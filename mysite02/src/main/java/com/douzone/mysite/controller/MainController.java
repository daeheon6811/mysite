package com.douzone.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mysite.web.main.MainActionFactory;


// @WebServlet({"/main","/"})
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	

	@Override
	public void init() throws ServletException {
		String configpath=  getServletConfig().getInitParameter("config");
		System.out.println("MainController.init().called" + configpath);
		super.init();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	request.setCharacterEncoding("UTF-8");

		String actionName = request.getParameter("a");

		Action action = new MainActionFactory().getAction(actionName);
		action.execute(request, response);
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
