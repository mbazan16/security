package com.example.security.configuration;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	public static final Logger logger = LoggerFactory.getLogger(MyBasicAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
			throws IOException {
		logger.info("[MyBasicAuthenticationEntryPoint- commence");
		// response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() );
		logger.info("HTTP Status 401 - " + authEx.getMessage());		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		

	}

	@Override
	public void afterPropertiesSet() {
		setRealmName("SecRest");
		super.afterPropertiesSet();
	}

}
