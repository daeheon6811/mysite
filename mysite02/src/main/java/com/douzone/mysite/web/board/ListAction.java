package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserVo authUser = null;
		int cureentPage = 0;
		int first = 0;
		int second = 0;
		int blockStartNum = 0;
		int blockLastNum = 0;
		int lastPageNum = 0;
		int pagesize = Paging.getPagecount();
		

		
		

		Paging paging = new Paging();

		String cp = request.getParameter("page");

		/* 최초 홈페이지 입장 */
		if (cp == null || "null".equals(cp)) {
			paging.makeBlock(cureentPage);
			paging.makeLastPageNum();
			blockStartNum = paging.getBlockStartNum(); // 그룹 번호
			blockLastNum = paging.getBlockLastNum();
			lastPageNum = paging.getLastPageNum();
			request.setAttribute("curPageNum", cureentPage);
		} else { /* 페이징 버튼을 눌렸을때 */

			cureentPage = Integer.parseInt(request.getParameter("page"));
			paging.makeBlock(cureentPage);
			paging.makeLastPageNum();
			blockStartNum = paging.getBlockStartNum(); // 그룹 번호
			blockLastNum = paging.getBlockLastNum();
			lastPageNum = paging.getLastPageNum();
			request.setAttribute("curPageNum", cureentPage);
		}
		request.setAttribute("blockStartNum", blockStartNum);
		request.setAttribute("blockLastNum", blockLastNum);
		request.setAttribute("lastPageNum", lastPageNum); // lastPageNum = 6일 때, 7, 8, 9, 10는 링크를 활성화 하지 못함

		if (cureentPage != 0) {
			first = (cureentPage * pagesize) - pagesize;
		} else {
			first = (cureentPage * pagesize);
		}
		second = (cureentPage * pagesize) + (pagesize - 1);

		List<BoardVo> list = new BoardRepository().findAll(first, pagesize);

		request.setAttribute("list", list);
		MvcUtil.forward("board/list", request, response);

	}

}

class Paging {
	private final static int pageCount = 5;
	private int blockStartNum = 0;
	private int blockLastNum = 0;
	private int lastPageNum = 0;

	public static int getPagecount() {
		return pageCount;
	}

	public int getBlockStartNum() {
		return blockStartNum;
	}

	public void setBlockStartNum(int blockStartNum) {
		this.blockStartNum = blockStartNum;
	}

	public int getBlockLastNum() {
		return blockLastNum;
	}

	public void setBlockLastNum(int blockLastNum) {
		this.blockLastNum = blockLastNum;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	public void makeBlock(int curPage) {
		int blockNum = 0;

		blockNum = (int) Math.floor((curPage - 1) / pageCount);
		blockStartNum = (pageCount * blockNum) + 1;
		blockLastNum = blockStartNum + (pageCount - 1);
	}

	public void makeLastPageNum() {

		int total = new BoardRepository().findAllCount();

		if (total % pageCount == 0) {
			lastPageNum = (int) Math.floor(total / pageCount);
		} else {
			lastPageNum = (int) Math.floor(total / pageCount) + 1;
		}
	}

	// 검색을 했을 때 총 페이지의 마지막 번호
	public void makeLastPageNum(String kwd) {

		int total = new BoardRepository().findAllCount();

		if (total % pageCount == 0) {
			lastPageNum = (int) Math.floor(total / pageCount);
		} else {
			lastPageNum = (int) Math.floor(total / pageCount) + 1;
		}
	}

}