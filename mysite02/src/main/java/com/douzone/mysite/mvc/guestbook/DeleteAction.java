package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mysite.dao.GuestBookDao;
import com.douzone.mysite.mvc.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		Integer no = Integer.parseInt(request.getParameter("no"));

		new GuestBookDao().Delete(no, password);
		MvcUtil.redirect(request.getContextPath() + "/guestbook?a=guestbook", request, response);		
		
	}
	
	

}
