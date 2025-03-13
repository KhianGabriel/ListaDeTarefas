package appweb.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Basic Hibernate helper class, handles SessionFactory, Session and
 * Transaction.
 * <p>
 * Uses a static initializer for the initial SessionFactory creation and holds
 * Session and Transactions in thread local variables. All exceptions are
 * wrapped in an unchecked InfrastructureException.
 *
 * @author christian@hibernate.org
 */
public class JpaUtil {

	private static Logger log = LoggerFactory.getLogger(JpaUtil.class);

	private static String PERSISTENCE_UNIT = "ERP";
	private static EntityManagerFactory factory;

	private static final ThreadLocal<EntityManager> threadManager = new ThreadLocal<>();
	private static final ThreadLocal<EntityTransaction> threadTransaction = new ThreadLocal<>();

	// Create the initial SessionFactory from the default configuration files
	static {
		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			log.debug("Builded EntityManagerFactory.");
		} catch (Throwable ex) {
			// We have to catch Throwable, otherwise we will miss
			// NoClassDefFoundError and other subclasses of Error
			log.error("Building EntityManagerFactory failed.", ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Returns the original Hibernate configuration.
	 *
	 * @return Configuration
	 */
	public static EntityManagerFactory getEntityNanagerFactory() {
		return factory;
	}

	/**
	 * Rebuild the SessionFactory with the static Configuration.
	 *
	 */
	public static void rebuildEntityManagerFactory() {
		synchronized (factory) {
			try {
				factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static EntityManager getManager() {
		EntityManager em = (EntityManager) threadManager.get();
		try {
			if (em == null) {
				log.debug("Opening new EntityManager for this thread: " + Thread.currentThread().getName());

				em = getEntityNanagerFactory().createEntityManager();

				threadManager.set(em);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		log.debug("return EntityManager, Thread: " + Thread.currentThread().getName());
		return em;
	}
	
	public static EntityManager getEntityManager() {
		EntityManager em = (EntityManager) threadManager.get();
		try {
			if (em == null) {
				log.debug("Opening new EntityManager for this thread: " + Thread.currentThread().getName());

				em = getEntityNanagerFactory().createEntityManager();

				threadManager.set(em);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		log.debug("return EntityManager, Thread: " + Thread.currentThread().getName());
		return em;
	}

	/**
	 * Closes the EntityManager local to the thread.
	 */
	public static void closeManager() {
		try {
			EntityManager em = (EntityManager) threadManager.get();
			// threadManager.set(null);
			threadManager.remove();
			if (em != null && em.isOpen()) {
				log.debug("Closing EntityManager of this thread: " + Thread.currentThread().getName());
				em.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Start a new database transaction.
	 */
	public static void beginTransaction() {
		EntityTransaction tx = (EntityTransaction) threadTransaction.get();
		try {
			if (tx == null) {
				log.debug("Starting new database transaction in this thread: " + Thread.currentThread().getName());
				tx = getManager().getTransaction();
				tx.begin();
				threadTransaction.set(tx);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Commit the database transaction.
	 */
	public static void commitTransaction() throws Exception {
		EntityTransaction tx = (EntityTransaction) threadTransaction.get();
		EntityManager em = (EntityManager) threadManager.get();

		try {
			if (em != null && tx != null) {
				log.debug("Committing database transaction of this thread: " + Thread.currentThread().getName());
				tx.commit();
			}
			// threadTransaction.set(null);
			threadTransaction.remove();

		} catch (Exception ex) {
			rollbackTransaction();
			throw ex;
		}
	}

	/**
	 * Commit the database transaction.
	 */
	public static void rollbackTransaction() {
		EntityTransaction tx = (EntityTransaction) threadTransaction.get();
		try {
			// threadTransaction.set(null);
			threadTransaction.remove();
			if (tx != null) {
				log.debug(
						"Tyring to rollback database transaction of this thread: " + Thread.currentThread().getName());
				tx.rollback();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeManager();
		}
	}

	/**
	 * Register a Hibernate interceptor with the current thread.
	 * <p>
	 * Every Session opened is opened with this interceptor after registration. Has
	 * no effect if the current Session of the thread is already open, effective on
	 * next close()/getSession().
	 */

	public static void clear() {
		if (factory != null)
			synchronized (factory) {
				try {
					EntityManager em = threadManager.get();
					if (em != null)
						em.close();

					factory.close();
					factory = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
	}

}
