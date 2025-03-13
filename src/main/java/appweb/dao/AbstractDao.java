package appweb.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import appweb.util.BOMessages;
import appweb.util.JpaUtil;
import appweb.util.MessageAlert;
import appweb.util.TextUtil;

public abstract class AbstractDao<T> implements IBaseDao<T> {
	private Class<T> clazz;
	//protected static Object lock = new Object();
	//protected Object saveLock = new Object();
	//protected Object updateLock = new Object();
	//protected Object deleteLock = new Object();

	private List<MessageAlert> listErros = new ArrayList<>();
	private List<MessageAlert> listWarnings = new ArrayList<>();
	private List<MessageAlert> listMessages = new ArrayList<>();
	private Long userId = 2L;

	public AbstractDao(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public Long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Override
	public EntityManager getEm() {
		return JpaUtil.getManager();
	}
	
	@Override
	public EntityManager getEntityManager() {
		return JpaUtil.getManager();
	}
	
	@Override
	public void beginTransaction() {
		JpaUtil.beginTransaction();
	}

	@Override
	public void commitTransaction() throws Exception {
		JpaUtil.commitTransaction();
	}
	
	@Override
	public void rollbackTransaction(){
		JpaUtil.rollbackTransaction();
	}
	
	@Override
	public T find(Object id) {
		T entity = null;
		try {
			entity = getEm().find(clazz, id);
		} catch (NoResultException e) {
			getListErros().add(new MessageAlert(MessageAlert.MSG_DADOS, BOMessages.notFound()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public <A> A find(Class<A> clazz, Object id) {
		return getEm().find(clazz, id);
	}

	public Long getId(T object) {
		Long id = null;
		try {
			// Classe a qual o objeto pertence
			Class<?> classe = object.getClass();
			Method methodGet;
			methodGet = classe.getDeclaredMethod("getId");
			if (!TextUtil.isEmpty(methodGet)) {
				id = (Long) methodGet.invoke(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void save(T entity) {
		try {
			clearAll();
			//synchronized (saveLock) {
				beginTransaction();
				formatSave(entity);
				if (validateSave(entity)) {					
					getEm().persist(entity);
					getListMessages().add(new MessageAlert(BOMessages.sucess()));
				}
				commitTransaction();
			//}
		} catch (Exception e) {
			rollbackTransaction();
			getListErros().add(new MessageAlert(MessageAlert.MSG_DADOS, BOMessages.notInsert()));
			e.printStackTrace();
		}
	}

	@Override
	public void update(T entity) {
		try {
			clearAll();
			//synchronized (updateLock) {
				beginTransaction();
				formatUpdate(entity);
				if (validateUpdate(entity)) {
					getEm().merge(entity);					
					getListMessages().add(new MessageAlert(BOMessages.sucess()));
				}
				commitTransaction();
			//}
		} catch (Exception e) {
			rollbackTransaction();
			getListErros().add(new MessageAlert(MessageAlert.MSG_DADOS, BOMessages.notAltered()));
			e.printStackTrace();
		}
	}

	@Override
	public void delete(T entity) {
		try {
			clearAll();
			//synchronized (deleteLock) {
				beginTransaction();
				if (validateDelete(entity)) {
					getEm().remove(entity);
					getListMessages().add(new MessageAlert(BOMessages.sucess()));
				} else
					getListErros().add(new MessageAlert(MessageAlert.MSG_DADOS, BOMessages.notRemove()));
				commitTransaction();
			//}
		} catch (Exception e) {
			rollbackTransaction();
			getListErros().add(new MessageAlert(MessageAlert.MSG_DADOS, BOMessages.notRemove()));
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(Long id) {
		// System.err.println("Id: " + id);
		try {
			clearAll();
			beginTransaction();
			T entity = find(id);
			// System.err.println("Validade: " + validateDelete(entity));
			if (validateDelete(entity)) {
				getEm().remove(entity);
				getListMessages().add(new MessageAlert(BOMessages.sucess()));
			}
			commitTransaction();
		} catch (Exception e) {
			rollbackTransaction();
			getListErros().add(new MessageAlert(MessageAlert.MSG_DADOS, BOMessages.notRemove()));
			e.printStackTrace();
		}
	}
	
	@Override
	abstract public boolean validate(T entity);

	@Override
	abstract public boolean validateSave(T entity);

	@Override
	abstract public boolean validateUpdate(T entity);

	@Override
	abstract public boolean validateDelete(T entity);
	
	@Override
	public List<MessageAlert> getListErros() {
		return listErros;
	}

	@Override
	public void setListErros(List<MessageAlert> listErros) {
		this.listErros = listErros;
	}

	@Override
	public List<MessageAlert> getListWarnings() {
		return listWarnings;
	}

	@Override
	public void setListWarnings(List<MessageAlert> listWarnings) {
		this.listWarnings = listWarnings;
	}

	@Override
	public List<MessageAlert> getListMessages() {
		return listMessages;
	}

	@Override
	public void setListMessages(List<MessageAlert> listMessages) {
		this.listMessages = listMessages;
	}
	
	@Override
	public void clearAll() {
		listErros.clear();
		listWarnings.clear();
		listMessages.clear();
	}

	@Override
	public abstract void format(T object);
	
	@Override
	public void formatSave(T object) {
		try {
			format(object);
			// Classe a qual o objeto pertence
			Class<?> classe = object.getClass();
			Method methodSet;
			methodSet = classe.getDeclaredMethod("setCreatedAt", Date.class);
			if (!TextUtil.isEmpty(methodSet)) {
				methodSet.invoke(object, new Date());
			}
			
			try {
				if (userId != null) {
//					methodSet = classe.getDeclaredMethod("setCreatedBy", Usuario.class);
//					if (!TextUtil.isEmpty(methodSet)) {
//						methodSet.invoke(object, getEm().find(Usuario.class, userId));
//					}
				}				
			}
			catch (NoSuchMethodError e) {
				// ignore
			}
			catch (Exception e) {
				e.printStackTrace();
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void formatUpdate(T object) {
		try {
			format(object);
			// Classe a qual o objeto pertence
			Class<?> classe = object.getClass();
			Method methodSet;
			methodSet = classe.getDeclaredMethod("setUpdatedAt", Date.class);
			if (!TextUtil.isEmpty(methodSet)) {
				methodSet.invoke(object, new Date());
			}
			
			try {			
				if (userId != null) {
					//System.err.format("UserId: %s%n", userId);
//					methodSet = classe.getDeclaredMethod("setUpdatedBy", Usuario.class);					
//					//System.err.format("methodSet: %s%n", methodSet);
//					if (!TextUtil.isEmpty(methodSet)) {
//						methodSet.invoke(object, getEm().find(Usuario.class, userId));						
//					}
				}				
			}
			catch (NoSuchMethodError e) {
				// ignore
			} 
			catch (Exception e) {			
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean hasErros() {
		return getListErros().size() > 0;
	}

	@Override
	public boolean isValid() {
		return getListErros().size() == 0;
	}

	@Override
	public boolean hasWarnings() {
		return getListWarnings().size() > 0;
	}

	@Override
	public boolean hasMessages() {
		return getListMessages().size() > 0;
	}
	
	@Override
	public List<T> loadAll() {
		EntityManager em = JpaUtil.getManager();
		return em.createQuery("from " + clazz.getName(), clazz).getResultList();
	}

	@Override
	public TypedQuery<T> createQuery(String jpql) {
		return getEm().createQuery(jpql, clazz);
	}
	
	@Override
	public <A> TypedQuery<A> createQuery(String jpql, Class<A> clazz) {
		return getEm().createQuery(jpql, clazz);
	}

	@Override
	public <A> List<A> getListByQuery(String jpql, Class<A> clazz) {
		EntityManager em = JpaUtil.getManager();
		return em.createQuery(jpql, clazz).getResultList();
	}
	
	@Override
	public List<T> getListByQuery(String jpql) {
		EntityManager em = JpaUtil.getManager();
		return em.createQuery(jpql, clazz).getResultList();
	}

	@Override
	public <A> List<A> getListByQuery(String jpql, Class<A> clazz, int offset, int size) {
		EntityManager em = JpaUtil.getManager();
		return em.createQuery(jpql, clazz).getResultList();
	}
	
	@Override
	public List<T> getListByQuery(String jpql, int offset, int size) {
		EntityManager em = JpaUtil.getManager();
		return em.createQuery(jpql, clazz).getResultList();
	}
}
