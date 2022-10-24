package com.example.security.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler{

	
	 public static final Logger logger = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);
    @Override

    public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {

    	logger.info("[CustomLogoutSuccessHandler- onLogoutSuccess");
		logger.info("Principal - " + authentication.getPrincipal());
		logger.info("response status"+response.getStatus());
		response.sendRedirect("/");
    }


}
