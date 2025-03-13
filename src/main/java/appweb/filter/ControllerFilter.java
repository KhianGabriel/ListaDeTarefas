package appweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import appweb.controller.Controller;
import appweb.util.TextUtil;



//@WebFilter(urlPatterns = { "/mvw" },  
//	initParams = { @WebInitParam(name = "baseDomain", value = "org.aznet.controller") })
public class ControllerFilter implements Filter {
	private FilterConfig config = null;

	private String baseDomain = "";
	private static Logger log = LoggerFactory.getLogger(ControllerFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
		baseDomain = config.getInitParameter("baseDomain");
		log.info("Starting Controller Filter");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// Verifica de o usuário está logado.
		String controllerClass = "";
		String view = "";
		try {
			HttpServletResponse response = (HttpServletResponse) res;
			HttpServletRequest request = (HttpServletRequest) req;

			String crontoller = request.getParameter("cl");
			view = request.getParameter("vw");
			controllerClass = crontoller + "Controller";

			String resultPage = "";
			Controller controller = null;

			if (!TextUtil.isEmpty(crontoller)) {
				log.debug("controllerClass: {}", controllerClass);
				controller = (Controller) Class.forName(baseDomain + "." + controllerClass).newInstance();
				resultPage = controller.execute(request, response);
			} else {
				log.debug("Parameter class not set");
			}

			log.debug("resultPage: {}, view: {}", resultPage, view);
			if (controller == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp");
				rd.forward(request, response);
			} else if (controller.getType() == Controller.FORWARD) {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + resultPage + ".jsp");
				rd.forward(request, response);
			} else
				response.sendRedirect(request.getContextPath() + "/" + resultPage);			

		} catch (ClassNotFoundException e) {
			log.debug("Controller class: [{}] not found", controllerClass);
			log.debug("View page: [" + view + "] not found");
			log.debug("ClassNotFoundException: {}", e.getMessage());
		} catch (Exception e) {
			// e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	public void destroy() {
	}
}