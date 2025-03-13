package appweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appweb.dao.ToDoListDao;
import appweb.model.ToDoList;
import appweb.util.BeanUtils;
import appweb.util.TextUtil;

public class ToDoListController extends AjaxControllerAdapter {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String opt = TextUtil.cast(request.getParameter("opt"));
			ToDoList toDoList = new ToDoList();
			BeanUtils.populate(toDoList, request.getParameterMap());
			ToDoListDao dao = new ToDoListDao();
			setUser(dao);
			
			switch (opt) {
				case "load":
					toDoList = dao.find(toDoList.getId());
					add(toDoList);
					break;
				case "save":
					dao.save(toDoList);
					add(toDoList);
					break;
				case "update":
					dao.update(toDoList);
					add(toDoList);
					break;
				case "updateStatus":
					dao.updateStatus(toDoList);
					add(toDoList);
					break;
				case "delete":
					dao.deleteById(toDoList.getId());
					break;
				case "listAll":					
					setListObject(dao.listAll());
					break;
			}
			
			setDao(dao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
