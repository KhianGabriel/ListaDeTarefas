package appweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appweb.dao.IBaseDao;
import appweb.util.BaseSystemObject;

public abstract class AjaxControllerAdapter implements AjaxController {
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected List<Object> listObject = new ArrayList<>();
	protected BaseSystemObject baseObj = new BaseSystemObject();
	protected long totalRecords;
	protected long totalRecordsFiltered;
	protected boolean redirect = false;
	protected boolean getSet = false;
	protected String url = "";
	protected IBaseDao<?> dao;
	protected Long userId;

	public AjaxControllerAdapter() {

	}
	
	@Override
	public void setHttpObjects(HttpServletRequest request, HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
	}
	
	@Override
	public <T> void setUser(IBaseDao<T> dao)
	{
		/*if (dao != null && getUserId() != null) {
			dao.setUserId(getUserId());
		} else if (request != null && dao != null) {
			SessionManager sessionManager = (SessionManager) request.getSession().getAttribute("sessionManager");
			if (sessionManager != null)
				dao.setUserId(sessionManager.getUsuario().getId());
		}*/
	}

	@Override
	public Long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Object> getListObject() {
		return listObject;
	}
	
	public Object getObject() {
		if (listObject.size() != 0)
			return listObject.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public void setListObject(List<?> listObject) {
		this.listObject = (List<Object>) listObject;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isGetSet() {
		return getSet;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public long getTotalRecordsFiltered() {
		return totalRecordsFiltered;
	}

	public void setTotalRecordsFiltered(long totalRecordsFiltered) {
		this.totalRecordsFiltered = totalRecordsFiltered;
	}

	public BaseSystemObject getBaseObj() {
		return baseObj;
	}

	public void setBaseObj(BaseSystemObject baseObj) {
		this.baseObj = baseObj;
	}

	public IBaseDao<?> getDao() {
		return dao;
	}

	@Override
	public void addList(List<Object> list) {
		this.listObject = list;
	}

	@Override
	public void add(Object object) {
		this.listObject.add(object);
	}

	@Override
	public void add(Object object, boolean getSet) {
		this.getSet = getSet;
		this.listObject.add(object);
	}

	@Override
	public <T> void setDao(IBaseDao<T> dao) {
		this.dao = dao;
		getBaseObj().addAllMessages(dao);
	}
	
	@Override
	public void setBso(BaseSystemObject bso) {		
		//this.dao = dao;
		getBaseObj().addAllMessages(bso);
	}

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
		out.println("\"results\": " + getBaseObj().toJson(getListObject()) + " }");
	}
}
