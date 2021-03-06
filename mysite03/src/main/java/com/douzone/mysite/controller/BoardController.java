package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.Paging.Paging;
import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	@RequestMapping({ "kwd" })
	public String index(@RequestParam("kwd") String kwd ,   Model model) {

		if (kwd == null || kwd.isEmpty()) {
			kwd = "";
		}
		int first = 0;
		int pagesize = Paging.getPagecount();
		Paging paging = new Paging();

		int cureentPage = 0;
		paging.makeBlock(cureentPage);
		double  totalpage = boardService.findAllCount();
		paging.makeLastPageNum(totalpage,kwd);
		int blockStartNum = paging.getBlockStartNum(); // 그룹 번호
		int blockLastNum = paging.getBlockLastNum();
		int lastPageNum = paging.getLastPageNum();

		model.addAttribute("curPageNum", cureentPage);
		model.addAttribute("blockStartNum", blockStartNum);
		model.addAttribute("blockLastNum", blockLastNum);
		model.addAttribute("lastPageNum", lastPageNum);
		if (cureentPage != 0) {
			first = (cureentPage * pagesize) - pagesize;
		} else {
			first = (cureentPage * pagesize);
		}
		List<BoardVo> list = boardService.findAllSearch(kwd, first, pagesize);
		model.addAttribute("list", list);
		return "/board/list";
	}

	@RequestMapping({ "page/{page}" })
	public String index(@PathVariable("page") int page, Model model) {
		String kwd = "";
		if (kwd == null || kwd.isEmpty()) {
			kwd = "";
		}
		int first = 0;
		int pagesize = Paging.getPagecount();
		Paging paging = new Paging();

		int cureentPage = page;
		paging.makeBlock(cureentPage);
		double  totalpage = boardService.findAllCount();
		paging.makeLastPageNum(totalpage,kwd);
		int blockStartNum = paging.getBlockStartNum(); // 그룹 번호
		int blockLastNum = paging.getBlockLastNum();
		int lastPageNum = paging.getLastPageNum();
		model.addAttribute("curPageNum", cureentPage);
		model.addAttribute("blockStartNum", blockStartNum);
		model.addAttribute("blockLastNum", blockLastNum);
		model.addAttribute("lastPageNum", lastPageNum);
		if (cureentPage != 0) {
			first = (cureentPage * pagesize) - pagesize;
		} else {
			first = (cureentPage * pagesize);
		}
		List<BoardVo> list = boardService.findAllSearch(kwd, first, pagesize);
		model.addAttribute("list", list);
		return "/board/list";
	}

	@RequestMapping("")
	@ExceptionHandler(Exception.class)
	public String index(Model model) {
		String kwd = "";
		int page = 0;
		if (kwd == null || kwd.isEmpty()) {
			kwd = "";
		}
		int first = 0;

		Paging paging = new Paging();
		int pagesize = Paging.getPagecount();

		int cureentPage = page;
		double  totalpage = boardService.findAllCount();
		paging.makeBlock(cureentPage);
		paging.makeLastPageNum(totalpage , kwd);
		int blockStartNum = paging.getBlockStartNum(); // 그룹 번호
		int blockLastNum = paging.getBlockLastNum();
		int lastPageNum = paging.getLastPageNum();

		model.addAttribute("curPageNum", cureentPage);
		model.addAttribute("blockStartNum", blockStartNum);
		model.addAttribute("blockLastNum", blockLastNum);
		model.addAttribute("lastPageNum", lastPageNum);
		if (cureentPage != 0) {
			first = (cureentPage * pagesize) - pagesize;
		} else {
			first = (cureentPage * pagesize);
		}
		List<BoardVo> list = boardService.findAllSearch(kwd, first, pagesize);

		model.addAttribute("list", list);
		return "/board/list";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(@AuthUser UserVo authUser) {
		System.out.println("write!");
		return "/board/write";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, BoardVo boardVo) {

		int maxGroupNo = boardService.findMaxGroupNo();

		System.out.println("유저 아이디 : " + authUser.getNo());

		boardVo.setHit(0);
		boardVo.setDepth(0);
		boardVo.setGroupNo(maxGroupNo + 1);
		boardVo.setOrderNo(1);
		boardVo.setUserNo(authUser.getNo());
		boardService.insert(boardVo);
		return "redirect:/board";
	}


	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String view(@PathVariable("no") Long no, Model model) {

		System.out.println(no);
		BoardVo boardvo = boardService.findByNo(no);// title , 내용 받아옴

		boardService.updateHit(no);
		model.addAttribute("title", boardvo.getTitle());
		model.addAttribute("contents", boardvo.getContents());
		model.addAttribute("no", no);
		model.addAttribute("userNo", boardvo.getUserNo());
		System.out.println(boardvo);

		return "board/view";
	}

	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, Model model) {

		model.addAttribute("no", no);
		return "board/modify";
	}

	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public String modify(@PathVariable("no") Long no, @RequestParam(value = "title") String title,
			@RequestParam(value = "contents") String contents) {
		
	
		boardService.updateView(no, title, contents);
		return "redirect:/board";
	}

	@Auth
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no) {

		boardService.delete(no);
		return "redirect:/board";
	}

	@Auth
	@RequestMapping(value = "/reply/{no}", method = RequestMethod.POST)
	public String reply(@AuthUser UserVo authUser, @PathVariable("no") Long no,
			@RequestParam(value = "groupNo") int groupNo, @RequestParam(value = "orderNo") int orderNo,
			@RequestParam(value = "depth") int depth, @RequestParam(value = "title") String title,
			@RequestParam(value = "contents") String contents) {

		BoardVo vo = new BoardVo();
		System.out.println("orderNo: " + orderNo + "title : " + title);
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setHit(0);
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo + 1);
		vo.setDepth(depth + 1);
		vo.setUserNo(authUser.getNo());
		System.out.println("vo값 이후 값 출력 : " + vo);
		boardService.insert(vo);

		return "redirect:/board";
	}

	@Auth
	@RequestMapping(value = "/reply/{no}", method = RequestMethod.GET)
	public String reply(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.findByNo(no);
		System.out.println("vo값 출력 : " + vo);
		model.addAttribute("vo", vo);
		return "board/reply";
	}
}
