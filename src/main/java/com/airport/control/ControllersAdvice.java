package com.airport.control;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class ControllersAdvice {
	
	@ExceptionHandler(value = RuntimeException.class)
	public String handleException(HttpSession req, Exception e) {
		req.removeAttribute("errorMess");
		req.setAttribute("errorMess", e.getMessage());
		return "redirect:/error";
	}
}
