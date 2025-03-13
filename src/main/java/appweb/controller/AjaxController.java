package appweb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appweb.dao.IBaseDao;
import appweb.util.BaseSystemObject;

public interface AjaxController {
	public void setHttpObjects(HttpServletRequest request, HttpServletResponse response);
	
	public <T> void setUser(IBaseDao<T> dao);
	
	public <T> void setDao(IBaseDao<T> dao);
	
	public <T> void setBso(BaseSystemObject bso);

	public void add(Object object);

	public void add(Object object, boolean getSet);

	public void addList(List<Object> object);
	
	public Long getUserId();

	public void setUserId(Long userId);

	public void createOutput(HttpServletRequest request, HttpServletResponse response) throws IOException;

	public void execute(HttpServletRequest request, HttpServletResponse response);
}
