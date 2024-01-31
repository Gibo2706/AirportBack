package com.airport.control;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.airport.response.body.ImageEncoder;
import com.airport.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	UserService us;
	
	@Autowired
	ImageEncoder ie;

	@GetMapping("/my-account")
	public String myAccount(HttpServletRequest req, Principal p) {
		req.setAttribute("korisnik", us.findByUsername(p.getName()));
		req.setAttribute("encoder", ie);
		return "myAccount";
	}

	@GetMapping("/my-flights")
	public String myFlights(Principal p, HttpServletRequest req) {
		System.out.println(p.getName());
		try {
			req.setAttribute("letovi", us.getFlights(us.findByUsername(p.getName())));
		} catch (Exception e) {
			req.setAttribute("letovi", null);
		}
		return "myFlights";
	}

	@GetMapping("/change-password")
	public String changePassword(HttpServletRequest req) {
		req.setAttribute("password", true);
		return "changeDetails";
	}

	@GetMapping("/change-email")
	public String changeEmail(HttpServletRequest req) {
		req.setAttribute("email", true);
		return "changeDetails";
	}

	@GetMapping("/change-username")
	public String changeUsername(HttpServletRequest req) {
		req.setAttribute("username", true);
		return "changeDetails";
	}

	@GetMapping("/change-phone")
	public String changePhone(HttpServletRequest req) {
		req.setAttribute("phone", true);
		return "changeDetails";
	}
	
	@GetMapping("/change-name")
	public String changeName(HttpServletRequest req) {
		req.setAttribute("name", true);
		return "changeDetails";
	}
	
	@GetMapping("/change-surname")
	public String changeSurname(HttpServletRequest req) {
		req.setAttribute("surname", true);
		return "changeDetails";
	}

	@PostMapping("/change-password-confirm")
	public String changePasswordConfirm(HttpServletRequest req, @RequestParam("inputPassword") String password,
			@RequestParam("retypePassword") String password2, Principal p) {
		if (password.equals(password2)) {
			us.changePassword(us.findByUsername(p.getName()), password);
			req.setAttribute("msg", "Password successfully changed!");
			return "myAccount";
		} else {
			req.setAttribute("msg", "Passwords don't match!");
			return changePassword(req);
		}
	}

	@PostMapping("/change-email-confirm")
	public String changeEmailConfirm(HttpServletRequest req, @RequestParam("inputEmail") String email,
			@RequestParam("retypeEmail") String email2, Principal p) {
		if (email.equals(email2)) {
			us.changeEmail(us.findByUsername(p.getName()), email);
			req.setAttribute("msg", "Email successfully changed!");
			return "myAccount";
		} else {
			req.setAttribute("msg", "Emails don't match!");
			return changeEmail(req);
		}
	}

	@PostMapping("/change-username-confirm")
	public String changeUsernameConfirm(HttpServletRequest req, @RequestParam("inputUsername") String username,
			@RequestParam("retypeUsername") String username2, Principal p) {
		if (username.equals(username2)) {
			us.changeUsername(us.findByUsername(p.getName()), username);
			req.setAttribute("msg", "Username successfully changed!");
			return "myAccount";
		} else {
			req.setAttribute("msg", "Usernames don't match!");
			return changeUsername(req);
		}
	}

	@PostMapping("/change-phone-confirm")
	public String changePhoneConfirm(HttpServletRequest req, @RequestParam("inputPhone") String phone,
			@RequestParam("retypePhone") String phone2, Principal p) {
		if (phone.equals(phone2)) {
			us.changePhone(us.findByUsername(p.getName()), phone);
			req.setAttribute("msg", "Phone number successfully changed!");
			return "myAccount";
		} else {
			req.setAttribute("msg", "Phone numbers don't match!");
			return changePhone(req);
		}
	}
	
	@PostMapping("/change-name-confirm")
	public String changeNameConfirm(HttpServletRequest req, @RequestParam("inputName") String name,
			@RequestParam("retypeName") String name2, Principal p) {
		if (name.equals(name2)) {
			us.changeName(us.findByUsername(p.getName()), name);
			req.setAttribute("msg", "Name successfully changed!");
			return "myAccount";
		} else {
			req.setAttribute("msg", "Names don't match!");
			return changeName(req);
		}
	}
	
	@PostMapping("/change-surname-confirm")
	public String changeSurnameConfirm(HttpServletRequest req, @RequestParam("inputSurname") String surname,
			@RequestParam("retypeSurname") String surname2, Principal p) {
		if (surname.equals(surname2)) {
			us.changeSurname(us.findByUsername(p.getName()), surname);
			req.setAttribute("msg", "Surname successfully changed!");
			return "myAccount";
		} else {
			req.setAttribute("msg", "Surnames don't match!");
			return changeSurname(req);
		}
	}
	
	@GetMapping("/change-profile-picture")
	public String changeProfilePicture(HttpServletRequest req) {
		req.setAttribute("profilePicture", true);
		return "changeDetails";
	}
	
	@PostMapping("/change-profile-picture-confirm")
	public String changeProfilePictureConfirm(HttpServletRequest req,
			@RequestParam("profilePicture") MultipartFile profilePicture, Principal p) throws IOException {
		if (profilePicture.isEmpty()) {
			req.setAttribute("msg", "You must choose a picture!");
			return changeProfilePicture(req);
		}
		if (profilePicture.getSize() > 60000) {
			req.setAttribute("msg", "Picture size must be less than 50KB!");
			return changeProfilePicture(req);
		}
		us.changeProfilePicture(us.findByUsername(p.getName()), profilePicture);
		req.setAttribute("msg", "Profile picture successfully changed!");
		return "myAccount";
	}

}
