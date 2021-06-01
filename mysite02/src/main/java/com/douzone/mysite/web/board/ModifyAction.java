package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long no = 	Long.parseLong(request.getParameter("no"));
		String title = 	request.getParameter("title");
		String contents = 	request.getParameter("contents");
		BoardVo boardvo = new BoardRepository().updateView(no , title , contents);
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=board", request, response);
		
		

	}

}
