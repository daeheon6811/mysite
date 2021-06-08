package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;



@Controller
@RequestMapping("/admin")
@Auth(role =  "ADMIN")
public class AdminController {
	
	@Autowired
	SiteService sitesevice;
	
	
	@Auth(role =  "ADMIN")
	@RequestMapping("")
	public String main(Model model) {
		
		SiteVo siteVo = sitesevice.findOne();
		model.addAttribute("siteVo",siteVo);
		return "admin/main";
	}
	
	@Auth(role =  "ADMIN")
	@RequestMapping(value = "/main/update ", method = RequestMethod.POST)
	public String updateMain(@ModelAttribute SiteVo vo) {
		vo.setProfile("/assets/images/profile.jpg");
		sitesevice.update(vo);
		return "redirect:/admin";
	}

	
	@Auth(role =  "ADMIN")
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@Auth(role =  "ADMIN")
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@Auth(role =  "ADMIN")
	@RequestMapping("/user")
	public String user() {
		return "admin/board";
	}

}
