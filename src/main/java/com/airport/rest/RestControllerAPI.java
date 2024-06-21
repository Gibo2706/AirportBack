package com.airport.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.dto.ConfirmBookDTO;
import com.airport.dto.KartaDTO;
import com.airport.dto.KorisnikDTO;
import com.airport.dto.LoginResponseDTO;
import com.airport.dto.RestLoginDTO;
import com.airport.dto.RestRegisterDTO;
import com.airport.dto.SearchReqDTO;
import com.airport.dto.SedisteReqDTO;
import com.airport.dto.UserFlightsReqDTO;
import com.airport.service.BookService;
import com.airport.service.UserService;

import jakarta.websocket.server.PathParam;
import model.Karta;
import model.Korisnik;
import model.Let;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/")
public class RestControllerAPI {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService us;

	@Autowired
	BookService bs;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody RestLoginDTO restLogin)
			throws NoSuchFieldException, SecurityException {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(restLogin.getUsername(), restLogin.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (authentication == null)
			return ResponseEntity.badRequest().body(new LoginResponseDTO("FAIL", null));
		if (authentication.isAuthenticated())
			return ResponseEntity.ok().body(new LoginResponseDTO("OK", authentication.getPrincipal()));
		else
			return ResponseEntity.badRequest().body(new LoginResponseDTO("FAIL", null));
	}

	@GetMapping("/countries")
	public ResponseEntity<?> getCountries() {
		return ResponseEntity.ok().body(us.getCountries());
	}

	@GetMapping("/airports")
	public ResponseEntity<?> getAirports() {
		return ResponseEntity.ok().body(bs.getAllAerodrom());
	}

	@PostMapping("/register")
	public ResponseEntity<LoginResponseDTO> register(@RequestBody RestRegisterDTO restRegister) {
		System.out.println(restRegister);
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
		if (authentication == null)
			return ResponseEntity.badRequest().body(new LoginResponseDTO("FAIL", null));
		if (authentication.isAuthenticated())
			return ResponseEntity.ok().body(new LoginResponseDTO("OK", authentication.getPrincipal()));
		else
			return ResponseEntity.badRequest().body(new LoginResponseDTO("FAIL", null));
	}

	@PostMapping("/flights")
	public ResponseEntity<?> getFlights(@RequestBody SearchReqDTO searchReq) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateS = null;
		Instant inst = null;
		try {
			inst = Instant.parse(searchReq.getDate());
			dateS = dateFormat.parse(searchReq.getDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(inst != null)
			dateS = Date.from(inst);
		Calendar c = Calendar.getInstance();
		c.setTime(dateS);
		c.add(Calendar.DATE, 1);
		Date dateE = c.getTime();
		List<Let> letovi = bs.getFlights(dateS, dateE, bs.getAerodrom(searchReq.getDeparture()).getId(),
				bs.getAerodrom(searchReq.getArrival()).getId());
		for (Let l : letovi) {
			System.out.println(l.getfNumber());
		}
		if (letovi != null)
			return ResponseEntity.ok().body(letovi);
		else
			return ResponseEntity.badRequest().body("Los zahtev");
	}

	@PostMapping("/seats")
	public ResponseEntity<?> getSeats(@RequestBody SedisteReqDTO sedisteReq) {
		return ResponseEntity.ok().body(bs.getSeatsForFlight(sedisteReq.getfNumber(), true));
	}

	@PostMapping("/book")
	public ResponseEntity<?> book(@RequestBody ConfirmBookDTO cbd) {
		Korisnik k = us.findByUsername(cbd.getUsername());
		System.out.println(k.getPassword().equals(cbd.getPassword()));
		if (k.getPassword().equals(cbd.getPassword()))
			return ResponseEntity.ok().body(bs.confirmFlightBook(cbd.getfNumber(), cbd.getSeats(), cbd.getUsername()));
		else
			return ResponseEntity.badRequest().body("FAIL");
	}

	@PostMapping("/userFlights")
	public ResponseEntity<?> userFlights(@RequestBody UserFlightsReqDTO restLogin) {
		System.out.println(restLogin.getUsername());
		Korisnik k = us.findByUsername(restLogin.getUsername());
		for(KartaDTO karta : us.getFlights(k,true)) {
			System.out.println(karta.getLet().getfNumber());
		}
		return ResponseEntity.ok().body(us.getFlights(k, true));
	}
	
	@PostMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody KorisnikDTO dto){
		System.out.println(dto);
		us.updateUser(dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping("/userProfile/{username}")
	public ResponseEntity<?> userProfile(@PathVariable("username") String username) {
		System.out.println(username);
		Korisnik k = us.findByUsername(username);
		return ResponseEntity.ok().body(k);
	}

	@GetMapping("/test")
	public String test() {
		System.out.println("Test");
		return "ok";
	}
}
