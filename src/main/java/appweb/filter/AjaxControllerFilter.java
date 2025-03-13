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

//import org.aznet.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import appweb.controller.AjaxController;
import appweb.util.TextUtil;

/*Intercepta todas as requisições que vierem do projeto ou mapeamento*/
public class AjaxControllerFilter implements Filter {
	private FilterConfig config = null;

	private String baseDomain = "";
	private static Logger log = LoggerFactory.getLogger(AjaxControllerFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
		baseDomain = config.getInitParameter("baseDomain");
		log.info("Starting AjaxController Filter");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// Verifica de o usuário está logado.
		String controllerClass = "";
		
		try {
			HttpServletResponse response = (HttpServletResponse) res;
			HttpServletRequest request = (HttpServletRequest) req;

			controllerClass = request.getParameter("cl") + "Controller";
			String modulePackage = request.getParameter("mod");
			modulePackage = modulePackage == null ? "": "." + modulePackage;
			//System.out.format( "STR: %s%n", controllerClass );
			log.debug("AjaxControllerClass: {}", controllerClass);

			if (!TextUtil.isEmpty(controllerClass)) {
				//System.err.println(baseDomain + modulePackage + "." + controllerClass);
				AjaxController controller = (AjaxController) Class.forName(baseDomain + modulePackage + "." + controllerClass)
						.newInstance();
				//System.err.format("Class: %s%n", controller);
				/*Usuario user = (Usuario)request.getSession().getAttribute("usuario");
				if (user != null)
					controller.setUserId(user.getId());*/
				controller.createOutput(request, response);
			}
		} catch (ClassNotFoundException e) {
			log.debug("AjaxControllerClass: [{}] not found", controllerClass);
		} catch (Exception e) {
			log.debug("AjaxControllerClass: [{}] not found", controllerClass);
			log.debug("Error: [{}] not found", e.getMessage());

		}
	}

	public void destroy() {
	}
}