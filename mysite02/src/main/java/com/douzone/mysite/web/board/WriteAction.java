package com.douzone.mysite.web.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.Action;
import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String Cureenttime = format.format(time);
		
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(request.getParameter("title"));
		boardVo.setContents(request.getParameter("contents"));
		boardVo.setHit(0);
		boardVo.setDepth(0);
		boardVo.setGroupNo(0);
		boardVo.setOrderNo(0);
		boardVo.setUserNo(authUser.getNo());
		
		new BoardRepository().insert(boardVo);
		
		System.out.println(authUser.getName());
		System.out.println(authUser.getNo());
		
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=board", request, response);
		
		
	}

}
