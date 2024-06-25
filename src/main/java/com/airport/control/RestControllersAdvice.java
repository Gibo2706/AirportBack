package com.airport.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpSession;

@RestControllerAdvice
public class RestControllersAdvice {
	
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<?> handleException(HttpSession req, Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
