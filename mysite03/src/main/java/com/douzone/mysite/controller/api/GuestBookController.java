package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller("guestbookControllerApi")
@RequestMapping("/guestbook/api")
public class GuestBookController {
	
	@Autowired
    GuestBookService guestBookService;
	
	
	
	//
	@ResponseBody
	@RequestMapping("/list")
	public JsonResult list(@RequestParam (value="no", required=true) String no) {
	
		 List<GuestBookVo> list = guestBookService.getMessageList(guestBookService.getMaxNo() - Long.parseLong(no));
		if(list.get(0) == null) {
			 list = guestBookService.getMessageList(guestBookService.getMaxNo() - (Long.parseLong(no) - 2L));
		}
		 System.out.println(no);
		 return JsonResult.success(list);
	}

	@ResponseBody
	@RequestMapping("/add")
	public JsonResult ex2(@RequestBody GuestBookVo vo) {
        
		guestBookService.addMessage(vo);
		
		return JsonResult.success(vo);
	    
		
	}
	@ResponseBody
	@RequestMapping("delete/{no}")
	public JsonResult ex2(@PathVariable("no") Long no , String password) {
        // 삭제 작업 (GuestbookService)
		Long data = 0L;
		
		if (!guestBookService.deleteMessage(no, password)) {
			data = -1L;
			return JsonResult.success(data);
		} 
	
		
		


		//  1. 삭제가 안된 경우
		data = -1L;
		
		// 2 삭제가 된 경우
		data = no;
		return JsonResult.success(data);
	    
		
	}
	
}
