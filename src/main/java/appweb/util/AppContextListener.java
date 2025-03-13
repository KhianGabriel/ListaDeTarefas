package appweb.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppContextListener implements ServletContextListener {
	private static Logger log = LoggerFactory.getLogger(AppContextListener.class);

	public AppContextListener() {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		log.info("Iniciando da aplicação: '{}'", arg0.getServletContext().getServletContextName());
		//MailUtil.setAppPath(arg0.getServletContext().getRealPath("/"));
		JpaUtil.getEntityNanagerFactory();

	}

	public void contextDestroyed(ServletContextEvent arg0) {
		//System.out.println("Fechando aplicação \"Estudo Remoto\"...");
		log.info("Fechando aplicação: '{}'", arg0.getServletContext().getServletContextName());
		// HibernateUtil.getSessionFactory().close();
		// HibernateUtil.clear();
	}

}
