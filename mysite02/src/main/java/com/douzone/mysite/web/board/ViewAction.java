package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		Long no = Long.parseLong( request.getParameter("no"));

		
		BoardVo boardvo =  new BoardRepository().findByNo(no); // title , 내용 받아옴
		
		new BoardRepository().updateHit(no);
		
	// 	System.out.println("no:" + no);
		request.setAttribute("title", boardvo.getTitle());
		request.setAttribute("contents", boardvo.getContents());
		request.setAttribute("no", no);
		request.setAttribute("userNo", boardvo.getUserNo());
	
		
		MvcUtil.forward("board/view", request, response);
		//request.setAttribute("reg_date", boardvo.getTitle());
		
		
		

	}

}
