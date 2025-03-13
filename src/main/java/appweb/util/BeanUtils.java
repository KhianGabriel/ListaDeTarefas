package appweb.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.commons.beanutils.converters.LongConverter;

public class BeanUtils {
	private static String MODEL_PACKAGE = "br.gov.ba.pmvc";
	private static String baseDomain = "";

	static {
		DateTimeConverter dateConverter = new DateConverter(null);
		dateConverter.setPatterns(new String[] { "dd/MM/yyyy", "MM/yyyy", "MM-yyyy", "dd-MM-yyyy HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm", "yyyy-MM-dd'T'HH:mm:ss.SSS",
				"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "dd/MM/yyyy HH:mm", "HH:mm", "HH:mm:ss" });
		
		//dateConverter.setPatterns(new String[] { "yyyy-MM-dd" });
		ConvertUtils.register(dateConverter, Date.class);

		LongConverter longConverter = new LongConverter(null);
		ConvertUtils.register(longConverter, Long.class);

		Converter stringConverter = new Converter() {
			@SuppressWarnings({ "rawtypes" })
			@Override
			public Object convert(Class clazz, Object value) {
				if (value != null) {
					String str = value.toString();
					// System.out.format("Person:[%s], [%s] %n", clazz.getTypeName(), value);
					str = str.trim();
					if (!str.isEmpty())
						return str;
				}
				return null;
			}
		};
		ConvertUtils.register(stringConverter, String.class);

		@SuppressWarnings("unused")
		Converter ModelConverter = new Converter() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Object convert(Class clazz, Object value) {
				Object objReturn = null;
				if (value != null) {
					String str = value.toString();
					Long id = null;
					try {
						id = Long.parseLong(str);
						try {
							objReturn = JpaUtil.getManager().find(clazz, id);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();

					}
				}
				return objReturn;
			}
		};

		@SuppressWarnings("unused")
		Converter ListModelConverter = new Converter() {
			@SuppressWarnings({ "rawtypes" })
			@Override
			public Object convert(Class clazz, Object value) {
				Object objReturn = null;
				try {
					objReturn = new ArrayList();
				} catch (Exception e) {
					e.printStackTrace();

				}
				return objReturn;
			}
		};
		//ConvertUtils.register(ListModelConverter, java.util.List.class);
		// ConvertUtils.register(CollectionsModelConverter, java.util.Set.class);

		/*ConvertUtils.register(ModelConverter, UF.class);
		ConvertUtils.register(ModelConverter, Municipio.class);
		ConvertUtils.register(ModelConverter, Usuario.class);
		ConvertUtils.register(ModelConverter, Grupo.class);
		ConvertUtils.register(ModelConverter, Pessoa.class);
		ConvertUtils.register(ModelConverter, Docente.class);
		ConvertUtils.register(ModelConverter, Serie.class);
		ConvertUtils.register(ModelConverter, Disciplina.class);
		ConvertUtils.register(ModelConverter, Curso.class);
		ConvertUtils.register(ModelConverter, Inscricao.class);
		ConvertUtils.register(ModelConverter, Meta.class);
		ConvertUtils.register(ModelConverter, MetaNota.class);*/

	}

	public static String getBaseDomain() {
		return baseDomain;
	}

	public static void setBaseDomain(String baseDomain) {
		BeanUtils.baseDomain = baseDomain;
		MODEL_PACKAGE = baseDomain + ".model";
	}

	private static String getValue(String key, Map<String, String[]> properties) {
		String value = null;
		try {
			String[] valueArray = (String[]) properties.get(key);

			if (valueArray != null && valueArray.length > 0) {
				value = valueArray[0];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static void populate(Object bean, Map<String, String[]> properties) {
		try {
			Field[] fields = bean.getClass().getDeclaredFields();

			for (Field field : fields) {
				String fieldValue = getValue(field.getName(), properties);

				if (fieldValue != null ) {
					if (field.getType().getName().startsWith(MODEL_PACKAGE)) {
						populateFromModel(bean, field, fieldValue);
					} else if (field.getType() == java.util.Set.class) {
						populateFromModelSet(bean, field, properties);
					} else if (field.getType() == java.util.List.class) {						
						populateFromModelList(bean, field, properties);
					} else {
						//System.out.format("fieldName: %s, fieldValue: %s%n", field.getName(), fieldValue);
						org.apache.commons.beanutils.BeanUtils.setProperty(bean, field.getName(), fieldValue);
						//bean.getClass().getDeclare
						//System.out.format("After - fieldName: %s, fieldValue: %s%n", be.getName(), fieldValue);
					}
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void populateFromModel(Object bean, Field field, String idValue)
			throws SecurityException, NoSuchFieldException {
		try {
			if (!TextUtil.isEmpty(idValue)) {

				Field idField = null;
				try {
					idField = field.getType().getDeclaredField("id");
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (!TextUtil.isEmpty(idField)) {
					if (idField.getType().getName().equals("long") || idField.getType() == java.lang.Long.class) {
						// System.out.println( "set" + TextCast.capitaliseWord( field.getName() ) );
						try {
							Long id = Long.parseLong(idValue);
							Object object = JpaUtil.getManager().find(field.getType(), id);
							Method methodSet = bean.getClass().getDeclaredMethod(
									"set" + TextUtil.capitaliseWord(field.getName()), field.getType());
							methodSet.invoke(bean, object);
						} catch (Exception e) {
							e.printStackTrace();
							// System.err.format( "Classe: %s%n", TextCast.capitaliseWord(
							// field.getName().substring( 0, field.getName().length() - 1 ) ) );
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void populateFromModelSet(Object bean, Field field, Map<String, String[]> properties) {
		try {
			// System.err.println( "id" + TextCast.capitaliseWord( field.getName() ) );
			String[] strIds = (String[]) properties.get("id" + TextUtil.capitaliseWord(field.getName()));

			if (strIds != null) {

				Class<?> classObj = null;

				if (strIds != null) {
					try {
						ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
						classObj = (Class<?>) stringListType.getActualTypeArguments()[0];
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (classObj != null) {
					Field idField = null;

					try {
						idField = classObj.getDeclaredField("id");
					} catch (Exception e) {
					}

					if (idField != null && idField.getType().getName().equals("long")
							|| idField != null && idField.getType() == java.lang.Long.class) {
						Set<Object> set = new HashSet<>();

						Method methodSet = bean.getClass().getDeclaredMethod(
								"set" + TextUtil.capitaliseWord(field.getName()), java.util.Set.class);
						methodSet.invoke(bean, set);
						for (int i = 0; i < strIds.length; i++) {
							try {
								Long id = Long.parseLong(strIds[i]);
								Object object = JpaUtil.getManager().find(classObj, id);
								set.add(object);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void populateFromModelList(Object bean, Field field, Map<String, String[]> properties) {
		try {
			// System.err.println( "idS: " + TextCast.capitaliseWord( field.getName() ) );
			String[] strIds = (String[]) properties.get(field.getName());

			if (strIds != null) {

				Class<?> classObj = null;

				if (strIds != null) {
					try {
						ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
						classObj = (Class<?>) stringListType.getActualTypeArguments()[0];
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (classObj != null) {
					Field idField = null;

					try {
						idField = classObj.getDeclaredField("id");
					} catch (Exception e) {
						// TODO: handle exception
					}

					if (!TextUtil.isEmpty(idField)) {
						if (idField.getType().getName().equals("long") || idField.getType() == java.lang.Long.class) {
							List<Object> list = new ArrayList<>();
							Method methodSet = bean.getClass().getDeclaredMethod(
									"set" + TextUtil.capitaliseWord(field.getName()), java.util.List.class);

							for (int i = 0; i < strIds.length; i++) {
								try {
									Long id = Long.parseLong(strIds[i]);
									Object object = JpaUtil.getManager().find(classObj, id);
									list.add(object);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							methodSet.invoke(bean, list);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
