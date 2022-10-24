package com.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorAppController  implements ErrorController{
	
	final static Logger logger = LoggerFactory.getLogger(ErrorAppController.class);
	
	@RequestMapping({"/error"})
	public String accesDenied(HttpServletRequest request,Model model) {
		
		logger.info("[ErrorController-error]");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String message = (String) request.getAttribute("javax.servlet.error.message");
		logger.info("statusCode:"+statusCode);
		logger.error("message:"+message);
		request.setAttribute("statusCode", statusCode);
		request.setAttribute("message", message);
		if(statusCode == HttpStatus.FORBIDDEN.value())
			 return "accessDenied";
		return getErrorPath();
	}

	@Override
	public String getErrorPath() {
		
		return "error";
	}

}
