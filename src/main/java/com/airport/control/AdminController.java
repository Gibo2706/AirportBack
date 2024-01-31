package com.airport.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.airport.service.AirlineService;
import com.airport.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	UserService userService;

	@Autowired
	AirlineService airlineService;

	@GetMapping("/")
	public String admin() {
		return "admin";
	}

	@PostMapping("/add-user")
	public String addUser(HttpServletRequest req, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("role") String role) {
		if (!userService.checkUsername(username) || !userService.checkPassword(password)
				|| !userService.checkRole(role)) {
			req.setAttribute("msg", "Invalid input!");
			return "admin";
		}
		int i = userService.addUser(username, password, email, role);
		req.setAttribute("msg", "User added successfully! ID: " + i);
		return "admin";
	}
	
	@PostMapping("/add-airline")
	public String addAirline(HttpServletRequest req, @RequestParam("airlineName") String name, @RequestParam("airlineEmail") String email) {
		if(!airlineService.checkName(name) || !airlineService.checkEmail(email)) {
            req.setAttribute("msg", "Invalid input!");
            return "admin";
        }
        int i = airlineService.addAirline(name, email);
        req.setAttribute("msg", "Airline added successfully! ID: " + i);
        return "admin";
	}

}
