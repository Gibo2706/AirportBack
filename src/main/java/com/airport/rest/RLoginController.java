package com.airport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.dto.LoginResponseDTO;
import com.airport.dto.RestLoginDTO;
import com.airport.dto.RestRegisterDTO;
import com.airport.service.UserService;

import model.Korisnik;

@RestController
@RequestMapping("/api/")
public class RLoginController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService us;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody RestLoginDTO restLogin) throws NoSuchFieldException, SecurityException {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(restLogin.getUsername(), restLogin.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (authentication==null)
			return ResponseEntity.badRequest().body(new LoginResponseDTO("FAIL", null));
		if (authentication.isAuthenticated())
			return ResponseEntity.ok().body(new LoginResponseDTO("OK",authentication.getPrincipal()));
		else
			return ResponseEntity.badRequest().body(new LoginResponseDTO("FAIL", null));
	}

	@PostMapping("/register")
	public String register(@RequestBody RestRegisterDTO restRegister) {
		Korisnik k = new Korisnik();
		k.setEmail(restRegister.getEmail());
		k.setName(restRegister.getFirstname());
		k.setSurname(restRegister.getLastname());
		k.setPassword(restRegister.getPassword());
		k.setPhone(restRegister.getPhone());
		k.setUsername(restRegister.getUsername());
		k.setDrzavaBean(us.findByCode(restRegister.getCountry()));
		us.save(k);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(restRegister.getUsername(), restRegister.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (authentication.isAuthenticated())
			return "OK";
		else
			return "FAIL";
	}
	
	@GetMapping("/test")
	public String test() {
		System.out.println("Test");
		return "ok";
	}
}
