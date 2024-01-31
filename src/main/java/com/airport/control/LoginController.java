package com.airport.control;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.airport.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.Korisnik;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	UserService us;

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		return "login";
	}

	@GetMapping("/register")
	public String showRegisterForm(HttpServletRequest req) {
		req.getSession().setAttribute("drzave", us.getDrzave());
		return "register";
	}

	@PostMapping("/registerUser")
	public String registerUser(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("fName") String fName, @RequestParam("lName") String lName,
			@RequestParam("phone") String phone, @RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword, @RequestParam("country") String code, Model model) {
		// Perform validation checks
	    if (username.isEmpty() || email.isEmpty() || fName.isEmpty() || lName.isEmpty()
	            || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
	        model.addAttribute("error", "All fields must be filled.");
	        return "register";
	    }

	    if (!Pattern.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", email)) {
	        model.addAttribute("error", "Invalid email format.");
	        return "register";
	    }

	    if (!password.equals(confirmPassword)) {
	        model.addAttribute("error", "Password and Confirm Password do not match.");
	        return "register";
	    }

	    // Check if the password has at least 8 characters
	    if (password.length() < 8) {
	        model.addAttribute("error", "Password must have at least 8 characters.");
	        return "register";
	    }

	    // Phone number validation (customize the regex based on your needs)
	    if (!Pattern.matches("^\\+?[0-9-]+$", phone)) {
	        model.addAttribute("error", "Invalid phone number format.");
	        return "register";
	    }
	    Korisnik k = new Korisnik();
	    k.setName(fName);
	    k.setSurname(lName);
	    k.setEmail(email);
	    k.setPassword(password);
	    k.setUsername(username);
	    k.setPhone(phone);
	    k.setDrzavaBean(us.findByCode(code));
	    us.save(k);
		return "index";
	}
	
	@GetMapping("/error")
	public String showErrorPage(HttpServletRequest req, HttpSession ses) {
		req.setAttribute("errorMess", ses.getAttribute("errorMess"));
		return "error";
	}

}
