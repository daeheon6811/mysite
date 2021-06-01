package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.util.MvcUtil;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Long no = Long.valueOf(request.getParameter("no"));
		
		BoardVo vo = new BoardRepository().findByNo(no);
		
		System.out.println("vo값 출력 : " + vo);
		
		
		request.setAttribute("vo", vo);
	
		MvcUtil.forward("board/reply", request, response);

	}

}
