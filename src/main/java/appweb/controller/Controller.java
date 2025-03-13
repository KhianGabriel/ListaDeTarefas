package appweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	public static final int REDIRECT = 1;
	public static final int FORWARD = 2;

	public void setType(int type);

	public int getType();

	public String execute(HttpServletRequest request, HttpServletResponse response);
}
