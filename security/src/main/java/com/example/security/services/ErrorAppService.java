package com.example.security.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorAppService  implements ErrorController{
	
	final static Logger logger = LoggerFactory.getLogger(ErrorAppService.class);
	
	@RequestMapping(value="/error", produces = MediaType.TEXT_PLAIN_VALUE)
	public String accesDenied(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		logger.info("[ErrorController-error]");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String message = (String) request.getAttribute("javax.servlet.error.message");
		logger.info("statusCode:"+statusCode);
		logger.error("message:"+message);
		request.setAttribute("statusCode", statusCode);
		request.setAttribute("message", message);
		if(statusCode == HttpStatus.FORBIDDEN.value())
			return "Acceso Denegado ";
		return "Se ha producido un error en el sistema: "+statusCode+ " message: "+message;
	}

	@Override
	public String getErrorPath() {
		
		return "/login";
	}

}
