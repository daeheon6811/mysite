package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	@ExceptionHandler(Exception.class)
	public String handlerException(Model model, Exception e) {
	
		// 1. 로깅(logging)
		//System.out.println(e);
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		System.out.println(errors);
		/*
		 * 1. appender
		 *    file appender /log-mysite/exception.1.log
		 *    10M(Archiving 정책)  
		 *    1-10(rolling)
		 *    
		 * 2. logger - com.douzone.mysite.exception , level(error)
		 *    logger - Root , level(debug) , console appender
		 * 
		 */
		LOGGER.error(errors.toString());
		//2. 사과 페이지
		
		//3. 정상 종료
		model.addAttribute("exception", errors.toString());
	
		
		return "/error/exception/";
		
	}
}
