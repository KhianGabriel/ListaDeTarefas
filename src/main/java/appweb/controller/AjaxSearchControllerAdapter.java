package appweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appweb.util.TextUtil;

public abstract class AjaxSearchControllerAdapter extends AjaxControllerAdapter {

	@Override
	public abstract void execute(HttpServletRequest request, HttpServletResponse response);

	public void createOutput(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		execute(request, response);
		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // evita o caching no servidor proxy.		

		String draw = TextUtil.cast(request.getParameter("draw"));

		PrintWriter out = response.getWriter();

		// Imprime o JSON na página
		out.println(String.format("{ \"draw\": %s, \"recordsTotal\": %s, \"recordsFiltered\": %s, \"data\": %s }",
				draw, getTotalRecords(), getTotalRecordsFiltered(), getBaseObj().toJson(getListObject(), false)));		
	}
}
