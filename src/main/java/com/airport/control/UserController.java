package com.airport.control;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airport.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	UserService us;
	
	@GetMapping("/my-account")
	public String myAccount() {
		return "myAccount";
	}
	
	@GetMapping("/my-flights")
	public String myFlights(Principal p, HttpServletRequest req) {
		System.out.println(p.getName());
		try {
		req.setAttribute("letovi", us.getFlights(us.findByUsername(p.getName())));
		}catch(Exception e) {
			req.setAttribute("letovi", null);
		}
		return "myFlights";
	}
}
