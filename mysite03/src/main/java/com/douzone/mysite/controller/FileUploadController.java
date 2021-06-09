package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileuploadService;

@Controller
public class FileUploadController {

	@Autowired
	private FileuploadService fileuploadservice;

	@RequestMapping({ "", "/form" })
	public String form() {
		return "/WEB-INF/views/form.jsp";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file1") MultipartFile file1,
			@RequestParam(value = "email", required = true, defaultValue = "") String email, Model model) {

		String url = fileuploadservice.restore(file1);

		model.addAttribute("url", url);

		return "/WEB-INF/views/result.jsp";
	}

}
