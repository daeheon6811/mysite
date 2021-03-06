package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {
	@Autowired
	private SiteService siteService;
	
	@RequestMapping("")
	public String index(Model model) {
		SiteVo vo = siteService.findOne();
		//model.addAttribute("vo", vo);
		return "main/index";
	}

	@ResponseBody
	@RequestMapping("/hello")
	public UserVo hello() {
		UserVo vo = new UserVo();
		vo.setNo(10L);
		vo.setEmail("wow1186@naver.com");
		vo.setName("박대헌");
		return vo;
	}

	@ResponseBody
	@RequestMapping("/msg1")
	public String message1() {
		return "안녕~~";
	}

	@ResponseBody
	@RequestMapping("/msg2")
	public UserVo message2() {
		UserVo vo = new UserVo();
		vo.setNo(1L);
		vo.setEmail("dd");
		vo.setName("dd");
		vo.setPassword("dd");
		return vo;
	}
}
