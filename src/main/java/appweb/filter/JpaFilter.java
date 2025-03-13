package appweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import appweb.util.JpaUtil;

public class JpaFilter implements Filter {

	private static Logger log = LoggerFactory.getLogger(JpaFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("JPA filter started, now opening/closing a Session for each request.");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		try {

			log.debug("--<< Start Filter >>--");
			log.debug("URL Query: {}/{}", request.getServletPath(), request.getQueryString());
			// JpaUtil.beginTransaction();

			chain.doFilter(request, response);

			log.debug("--<< End of Filter >>--");
			// Commit any pending database transaction.
			// JpaUtil.commitTransaction();

			// System.out.println( "--<<<<< Finalizando Transação >>>>--" );

		} catch (Exception e) {
			String url = request.getRequestURL().toString();
			String queryString = request.getQueryString();
			log.error("Url exception: {}", url);
			log.error("Query String: {}", queryString);
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			// log.debug("Closing EntityManger on HibernateFilter {}",
			// Thread.currentThread().getName());
			log.debug("Closing EntityManger");
			JpaUtil.closeManager();
			//JpaUtil.closeManager();
		}
	}

	public void destroy() {
		JpaUtil.clear();
		log.debug("Destroy EntityManger");
	}

}
