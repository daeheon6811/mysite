package com.douzone.mysite.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class LoginFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MvcUtil.forward("user/loginform", request, response);
	}

}
