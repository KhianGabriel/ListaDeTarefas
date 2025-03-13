package appweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import appweb.util.MessageAlert;

public interface IBaseDao<T> {

	public Long getUserId();
	
	public void setUserId(Long userId);
	
	public EntityManager getEm();

	public EntityManager getEntityManager();

	public void beginTransaction();

	public void commitTransaction() throws Exception;
	
	public void rollbackTransaction();

	public T find(Object id);
	
	public <A> A find(Class<A> clazz, Object id);

	public void save(T entity);

	public void update(T entity);

	public void delete(T entity);

	public void deleteById(Long id);
	
	public boolean validate(T entity);

	public boolean validateSave(T entity);

	public boolean validateUpdate(T entity);

	public boolean validateDelete(T entity);

	public void format(T entity);
	
	public void formatSave(T entity);

	public void formatUpdate(T entity);

	public boolean hasErros();

	public boolean hasWarnings();

	public boolean hasMessages();
	
	public boolean isValid();

	public List<MessageAlert> getListErros();
	
	public void setListErros(List<MessageAlert> listErros);

	public List<MessageAlert> getListWarnings();
	
	public void setListWarnings(List<MessageAlert> listWarnings);

	public List<MessageAlert> getListMessages();
	
	public void setListMessages(List<MessageAlert> listMessages);
	
	public void clearAll();
	
	public List<T> loadAll();
	
	public TypedQuery<T> createQuery(String jpql);
	
	public <A> TypedQuery<A> createQuery(String jpql, Class<A> clazz);		

	public List<T> getListByQuery(String jpql);	
	
	public <A> List<A> getListByQuery(String jpql, Class<A> clazz);	

	public List<T> getListByQuery(String jpql, int offset, int size);
	
	public <A> List<A> getListByQuery(String jpql, Class<A> clazz, int offset, int size);	

}
