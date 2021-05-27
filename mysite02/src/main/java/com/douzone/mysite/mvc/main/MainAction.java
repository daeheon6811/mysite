package com.douzone.mysite.mvc.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mysite.mvc.util.MvcUtil;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MvcUtil.forward("main/index", request, response);
	}
	
	

}
