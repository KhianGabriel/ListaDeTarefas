package appweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AjaxControllerDirectAdapter extends AjaxControllerAdapter {	
	public abstract void execute(HttpServletRequest request, HttpServletResponse response);

	public void createOutput(HttpServletRequest request, HttpServletResponse response) throws IOException {
		execute(request, response);
		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // evita o caching no servidor proxy.

		PrintWriter out = response.getWriter();

		// Imprime o JSON na página
		out.println("{ \"sucess\": " + getBaseObj().isValid() + ", \"redirect\": " + isRedirect()
				+ ", \"url\": \"" + getUrl() + "\", \"messages\": ");

		out.println(getBaseObj().getJsonMessages());

		out.println(", \"size\": " + getListObject().size() + ", ");
		out.println("\"results\": " + (getObject() == null? "[]" :  getObject()) + " }");
	}
}
