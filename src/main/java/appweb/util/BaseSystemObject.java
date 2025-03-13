package appweb.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;

import appweb.dao.IBaseDao;

public class BaseSystemObject {
	private static final String MODEL_PACKAGE = "appweb.model";
	private StringBuffer buffer = new StringBuffer();
	private List<MessageAlert> listErros = new ArrayList<>();
	private List<MessageAlert> listWarnings = new ArrayList<>();
	private List<MessageAlert> listMessages = new ArrayList<>();
	
	//private static SimpleDateFormat sdfData = new SimpleDateFormat( "dd/MM/yyyy" );
	//private static SimpleDateFormat sdfHora = new SimpleDateFormat( "HH:mm" );
	//private static SimpleDateFormat sdfHoraSS = new SimpleDateFormat( "HH:mm:ss" );
	private static SimpleDateFormat sdfIso = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");


	public List<MessageAlert> getListErros()
	{
		return listErros;
	}

	public void setListErros( List<MessageAlert> listErros )
	{
		this.listErros = listErros;
	}

	public List<MessageAlert> getListWarnings()
	{
		return listWarnings;
	}

	public List<MessageAlert> getListMessages()
	{
		return listMessages;
	}
	
	public String getMessagesStr()
	{
		String str = "";
		Iterator<MessageAlert> it = null;
		MessageAlert mAlert = null;
		
		if ( listErros.size() > 0 ) {
			it = listErros.iterator();
			
			str += "<div id= \"listErro\">";
			str += "<h2>Erro(s)</h2>";
			str += "<ul>";
			
			while ( it.hasNext() ) {
				mAlert = it.next();
				if ( mAlert.getTypeMessage() == 0 ) {
					str += "<li><label for=\"" + mAlert.getFieldName() + "\" >" + mAlert.getMessage() + "</label></li>";
				}
				else {
					str += "<li>" + mAlert.getMessage() + "</li>";
				}
			}

			str += "</ul>";
			str += "</div>";
		}
				
		if ( listWarnings.size() > 0 ) {
			it = listWarnings.iterator();
			
			str += "<div id= \"listAviso\">";
			str += "<h2>Aviso(s)</h2>";
			str += "<ul>";
			
			while ( it.hasNext() ) {
				mAlert = it.next();
				if ( mAlert.getTypeMessage() == 0 ) {
					str += "<li><label for=\"" + mAlert.getFieldName() + "\" >" + mAlert.getMessage() + "</label></li>";
				}
				else {
					str += "<li>" + mAlert.getMessage() + "</li>";
				}
			}

			str += "</ul>";
			str += "</div>";
		}

		if ( listMessages.size() > 0 ) {
			it = listMessages.iterator();
			
			str += "<div id= \"listMensagem\">";			
			str += "<ul>";
			
			while ( it.hasNext() ) {
				mAlert = it.next();
				if ( mAlert.getTypeMessage() == 0 ) {
					str += "<li><label for=\"" + mAlert.getFieldName() + "\" >" + mAlert.getMessage() + "</label></li>";
				}
				else {
					str += "<li>" + mAlert.getMessage() + "</li>";
				}
			}

			str += "</ul>";
			str += "</div>";
		}		
		
		listErros.clear();
		listWarnings.clear();
		listMessages.clear();
		
		return str;
	}
	
	public void addAllMessages( BaseSystemObject obj )
	{
		getListMessages().addAll( obj.getListMessages() );		
		getListWarnings().addAll( obj.getListWarnings() );
		getListErros().addAll( obj.getListErros() );
		obj.clearAll();
	}
	
	public <T> void addAllMessages( IBaseDao<T> obj )
	{
		getListMessages().addAll( obj.getListMessages() );		
		getListWarnings().addAll( obj.getListWarnings() );
		getListErros().addAll( obj.getListErros() );
		obj.clearAll();
	}
	
	public void clearAll()
	{
		getListMessages().clear();
		getListWarnings().clear();
		getListErros().clear();
	}
	
	public boolean hasErros()
	{
		return getListErros().size() > 0;
	}
	
	public boolean isValid()
	{
		return getListErros().size() == 0;
	}
	
	public boolean hasWarnings()
	{
		return getListWarnings().size() > 0;
	}
	
	public boolean hasMessages()
	{
		return getListMessages().size() > 0;
	}
	
	//--------------------------------------------------------------------
	// Iniciando parte do JSON
	//--------------------------------------------------------------------	
	public String toJson( Object object )
	{
		buffer.delete(0, buffer.length());
		objectToJson( object, false );
		return buffer.toString();
	}
	
	public <T> String toJson( List<T> list )
	{		
		return toJson( list, true );	
	}
	
	public <T> String toJson( List<T> list, boolean getSet )
	{
		buffer.delete(0, buffer.length());
		buffer.append(  "[ " );
		Iterator<T> it = list.iterator();
		if ( it.hasNext() ) {
			T t = it.next();
			objectToJson( t, getSet );			
		}		
		
		while ( it.hasNext() ) {
			T t = it.next();
			buffer.append(  ", " );
			objectToJson( t, getSet );
		}
		buffer.append(  " ]" );
		return buffer.toString();
	}
	
	public String toJson( Object object, boolean getSet )
	{
		buffer.delete(0, buffer.length());
		objectToJson( object, getSet );
		return buffer.toString();
	}
	
	public boolean checkField( Field field )
	{
		return ( !field.getName().contains( "$" ) &&  !field.getName().equals( "_methods_" )
			&&  !field.getName().toLowerCase().contains( "senha" )
			&&  !field.getName().equals( "password" )
			&&  !field.getName().equals( "handler" ) 
			&&  !field.getName().equals( "serialVersionUID" )			
			&&  !field.getName().equals( "_filter_signature" ) );
	
	}
	
	public boolean isNumber( Field field )
	{
		return ( field != null && (
				 field.getType() == boolean.class     ||
			 	 field.getType() == Boolean.class ||
			 	 field.getType() == int.class     ||
			 	 field.getType() == Integer.class ||
				 field.getType() == long.class    ||
			 	 field.getType() == Long.class    ||
				 field.getType() == float.class    ||
			 	 field.getType() == Float.class ||
			 	 field.getType() == double.class ||
			 	 field.getType() == Double.class ) );
	
	}
	
	public void dateToJson( Object object, Field field )
	{
		String propertyName = field.getName();
		Object objectValue = "";		
		try {	
			// Pega o método get ou is da propriedade
			String methodPrefix = "get";						
			String methodGetName = methodPrefix + TextUtil.capitaliseWord( propertyName );
			Method methodGet = object.getClass().getDeclaredMethod( methodGetName );
			objectValue = methodGet.invoke( object );
			
			
			/*if ( field.getName().startsWith( "dataCriacao" ) || field.getName().startsWith( "dataAlteracao" ) ||
					field.getName().startsWith( "dataLogin" ) || field.getName().startsWith( "dataLogout" ) ) {
				// Se não possui "hora" no nome, formata como data
				propertyName = "dt" + TextCast.capitaliseWord( propertyName );
				if ( objectValue != null ) {
					objectValue = sdfData.format( ( Date )objectValue ) + " as " + sdfHoraSS.format( ( Date )objectValue );
				}						
			}
			else if ( field.getName().toLowerCase().indexOf( "hora" ) == -1 ) {
				// Se não possui "hora" no nome, formata como data
				propertyName = "dt" + TextCast.capitaliseWord( propertyName );
				if ( objectValue != null ) {
					objectValue = sdfData.format( ( Date )objectValue );
				}						
			}
			else {
				// Se possui "hora" no nome, formata como hora
//				propertyName = "hr" + TextCast.capitaliseWord( propertyName );
//				if ( objectValue != null ) {
//					objectValue = sdfHora.format( ( Date )objectValue );
//				}	
				
				if ( objectValue != null ) {
					objectValue = ( ( Date )objectValue ).getTime();
				}	
			}*/
			if ( objectValue != null ) {
				objectValue = sdfIso.format( ( Date )objectValue );
			}	
			//System.err.format("[%s][%s][%s]%n", methodGetName, propertyName, objectValue);
			if (!TextUtil.isEmpty(objectValue))
				buffer.append(  ", \"" + propertyName + "\": \"" + objectValue + "\"");
		} catch (Exception e) {
			e.printStackTrace();
			
		}		
	}
	
	public void objectToJson( Object object, boolean getSet )
	{		
		if ( object != null ) {
			Class<?> objectClass = object.getClass();
			buffer.append( "{ \"class_type\": \"" + objectClass.getSimpleName() + "\"" );
			//Field f = null;
			try {
				// Pega as propriedades do objeto
				Field[] fields = objectClass.getDeclaredFields();			
				for ( Field field : fields ) {					
					if ( checkField( field ) ) {
						if ( field.getType() == java.util.Date.class ) {
							dateToJson( object, field );
						}
						else if ( field.getType() == java.util.Set.class ) {							
							if ( getSet ) { 
								// Pega o set
								setToJson( object, field );
							}
						}
						else if ( field.getType() == java.util.List.class ) {
							// Pega a propriedade
							if ( getSet ) { 
								listToJson( object, field );
							}
						}
						else if (field.getType().getName().startsWith( MODEL_PACKAGE )) {
							objectModelToJson( object, field );
						}							
						else {
							// Pega a propriedade
							objectToJson( object, field );
						}
					}
					//System.out.format("Tipo: %s, Nome: %s%n", field.getType(), field.getName());
				}				
			}
			catch ( Exception e ) {
				//System.out.println(	"Erro ao tentar identificar as propriedades. :" + object + " - " + f.getName() );
				//System.out.println(	"Erro ao tentar identificar as propriedades. :" + object + " - " + f.getName() );
				e.printStackTrace();
			}
			buffer.append(  " }" );
			
		}		
	}	
	
	private void objectToJson( Object object, Field field )
	{
		String propertyName = field.getName();
		Object objectValue = "";
		//---------------------------------------
		try
		{
			if ( !Modifier.isFinal(field.getModifiers()) ) {
				// Pega o método get ou is da propriedade
				String methodPrefix = "get";
				if ( field.getType() == boolean.class )
					methodPrefix = "is";
				
				try {
					try {
						String methodGetName = methodPrefix + TextUtil.capitaliseWord( propertyName );
						Method methodGet = object.getClass().getDeclaredMethod( methodGetName );
						// Pega o retorno do método get da propriedade
						objectValue = methodGet.invoke( object );
					}catch (Exception e) {
						// TODO: handle exception
					}
					
					if ( !TextUtil.isEmpty(objectValue) ) {
						String value = "";
						if ( isNumber( field ) ) 
							value = (String)TextUtil.cast( objectValue );
						else
							//value = "\"" + TextCast.cast( objectValue ) + "\"";
							value = "\"" + StringEscapeUtils.escapeJson( (String)TextUtil.cast( objectValue ) ) + "\"";
						buffer.append( ",\n \"" + propertyName + "\": " );		
						buffer.append( value );
					}
					else {
						buffer.append( ",\n \"" + propertyName + "\": " );		
						buffer.append( "null" );
					}
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
		}
		catch ( Exception e ) {
			System.out.println(	"Erro ao tentar pegar o valor da propriedade:" + field.getName() );
			e.printStackTrace();
		}		
	}
	
	private void objectToJson( Object object )
	{		
		buffer.append( "{ " );
		try {
			if ( object != null ) {
				Object objectValue = "";
				// Gera a propriedade idObject
				Method methodGetId = object.getClass().getDeclaredMethod( "getId" );
				Object objectId = methodGetId.invoke( object );		
				
				buffer.append( "\"class_type\": \"" + TextUtil.cast( object.getClass().getSimpleName() ) + "\"" );
				//System.out.println(	"class_type: " + object.getClass().getSimpleName() );
				buffer.append( ", \"id\": " + TextUtil.cast( objectId ) );
				
				// Gera a propriedade nomeObject			
				Method methodGetNome = object.getClass().getDeclaredMethod( "getNome" );
				Object objectNome = methodGetNome.invoke( object );
				objectValue = objectNome != null ? "\"" +StringEscapeUtils.escapeJson((String)objectNome) + "\"" : null;
				buffer.append( ", \"nome\": " + objectValue + " " );
				//buffer.append( ", \"nome\": \"" + TextCast.cast( objectValue ) + "\"" );
			}			
		}
		catch ( Exception ex ) {
			//System.out.println(	"Erro ao tentar pegar a propriedade de: " + object.getClass() );
			//ex.printStackTrace();
		}
		finally {
			buffer.append( " }" );
		}					
	}
	
	private void objectModelToJson( Object object, Field field )
	{					
		try {
			if ( object != null ) {		
				String propertyName = field.getName();
				buffer.append( ",\n \"" + propertyName + "\": " );
				Object objectValue = "";
				
				String methodGetName = "get" + TextUtil.capitaliseWord( propertyName );
				Method methodGet = object.getClass().getDeclaredMethod( methodGetName );
				
				// Pega o retorno do método get da propriedade
				Object objectModel = methodGet.invoke( object );
				
				if ( objectModel != null ) {
					buffer.append( "{ " );				
				
					buffer.append( "\"class_type\": \"" + TextUtil.cast( objectModel.getClass().getSimpleName() ) + "\"" );
					// Gera a propriedade idObject
					try {
						Method methodGetId = objectModel.getClass().getDeclaredMethod( "getId" );
						Object objectId = methodGetId.invoke( objectModel );
						//System.out.println(	"class_type: " + objectModel.getClass().getSimpleName() );
						buffer.append( ", \"id\": " + objectId );
					}
					catch (Exception e) {}
										
					try {
						// Gera a propriedade nomeObject						
						Method methodGetNome = objectModel.getClass().getDeclaredMethod( "getNome" );
						Object objectNome = methodGetNome.invoke( objectModel );
						objectValue = objectNome;
						//buffer.append( ", nome: \"" + StringEscapeUtils.escapeJson((String)TextCast.cast( objectValue )) + "\" " );
						buffer.append( ", \"nome\": \"" + TextUtil.cast( objectValue ) + "\"" );
					}
					catch (Exception e) {}
					
					try {
						// Gera a propriedade nomeObject						
						Method methodGetNome = objectModel.getClass().getDeclaredMethod( "getApelido" );
						Object objectNome = methodGetNome.invoke( objectModel );
						objectValue = objectNome;
						//buffer.append( ", nome: \"" + StringEscapeUtils.escapeJson((String)TextCast.cast( objectValue )) + "\" " );
						buffer.append( ", \"apelido\": \"" + TextUtil.cast( objectValue ) + "\"" );
					}
					catch (NoSuchMethodException e) {}
					
					buffer.append( " }" );
				}
				else
					buffer.append( "null" );
			}			
		}
		catch ( Exception ex ) {
			System.out.println(	"Erro ao tentar pegar a propriedade de: " + object.getClass() );
			ex.printStackTrace();
		}							
	}
	
	@SuppressWarnings("rawtypes")
	private void setToJson( Object object, Field field )
	{		
		String setName = field.getName();
		buffer.append( ",\n \"" + setName + "\": [ " );
		try
		{
			// Pega o método get da propriedade
			String methodGetName = "get" + TextUtil.capitaliseWord( setName );
			Method methodGet = object.getClass().getDeclaredMethod( methodGetName );
			// Pega o retorno do método get da propriedade
			try {
				Set set = (Set)methodGet.invoke( object );
				Iterator iterator = set.iterator();
				while( iterator.hasNext() ) {
					Object setObject = (Object) iterator.next();
					//toJson( setObject, false );
					objectToJson( setObject );
				}
			}
			catch( Exception e ) {	
			}
		}
		catch ( Exception ex ) {			
			System.out.println(	"Erro ao tentar pegar os dados do set." );
			ex.printStackTrace();
		}
		buffer.append( " ]" );		
	}
	
	@SuppressWarnings("rawtypes")
	private void listToJson( Object object, Field field )
	{		
		String setName = field.getName();
		buffer.append( ",\n \"" + setName + "\": [ " );
		try
		{
			// Pega o método get da propriedade
			String methodGetName = "get" + TextUtil.capitaliseWord( setName );
			Method methodGet = object.getClass().getDeclaredMethod( methodGetName );
			// Pega o retorno do método get da propriedade
			try {
				List set = (List)methodGet.invoke( object );
				Iterator iterator = set.iterator();
				if ( iterator.hasNext() ) {
					Object setObject = (Object) iterator.next();
					objectToJson( setObject );					
				}
				
				while( iterator.hasNext() ) {
					Object setObject = (Object) iterator.next();
					buffer.append( ", " );
					objectToJson( setObject );
				}
			}
			catch( Exception e ) {	
			}
		}
		catch ( Exception ex ) {			
			System.out.println(	"Erro ao tentar pegar os dados do set." );
			ex.printStackTrace();
		}
		buffer.append( " ]" );		
	}
	
	public String getJsonMessages()
	{
		String jsonMessageStr = "{";
		// Converte listErros
		jsonMessageStr += "\"erros\": " + toJson( listErros );
		// Converte listWarnings
		jsonMessageStr += ", \"warnings\": " + toJson( listWarnings );
		// Converte listMessages
		jsonMessageStr += ", \"messages\": " + toJson( listMessages );
		
		jsonMessageStr += "}";
		
		return jsonMessageStr;
	}
}

