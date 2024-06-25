package com.airport.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.airport.service.TokenService;
import com.airport.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
	
	@Autowired
	TokenService ts;
	
	@Autowired
	UserService us;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		if(url.contains("login") || url.contains("register") || !url.contains("api")) return true;
		System.out.println(request.getQueryString());
		String token = request.getHeader("token");
		int userId = Integer.parseInt(request.getHeader("userId"));
		boolean tokenValid = ts.isValidToken(userId, token);
		System.out.println(token + " isValid: " + tokenValid);
		if(!tokenValid)
			throw new RuntimeException("Token expired");
		return true;
	}
}
