package com.airport.control;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.airport.service.AirlineService;
import com.airport.service.BookService;
import com.airport.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aviokompanija;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/avio/")
public class AirlineController {

	@Autowired
	private AirlineService as;

	@Autowired
	private UserService us;

	@Autowired
	private BookService bs;

	@GetMapping("/details")
	public String getData(HttpServletRequest req, Principal p) {
		Aviokompanija airline = as.getAirline(us.findByUsername(p.getName()).getEmail());
		if (airline == null) {
			return "redirect:/";
		}
		req.getSession().setAttribute("airline", airline);
		req.getSession().setAttribute("airplaneList", as.getAirplanes(airline.getId()));
		req.getSession().setAttribute("pilotList", as.getPilots(airline.getNaziv()));
		req.getSession().setAttribute("flightList", as.getFlights(airline.getId()));
		req.getSession().setAttribute("rentedAirplaneList", as.getRentedAirplanes(airline.getId()));
		return "airlineDet";
	}

	@GetMapping("/rent")
	public String rentAirplane(HttpServletRequest req, Principal p) {
		getData(req, p);
		return "rent";
	}

	@PostMapping("/rentPlane")
	public String rentAirplane(HttpServletRequest reg, @RequestParam("tailNumber") String tailNumber,
			@RequestParam("renter") String rentersEmail) {
		if (tailNumber == null || tailNumber.equals("") || rentersEmail == null || rentersEmail.equals("")) {
			reg.setAttribute("msg", "All fields are required!");
			return rentAirplane(reg, reg.getUserPrincipal());
		}
		String msg = as.rentAirplane(tailNumber, as.getAirline(rentersEmail).getId());
		if (msg == null) {
			reg.setAttribute("msg", "Plane with that tail number does not exist!");
			return rentAirplane(reg, reg.getUserPrincipal());
		}
		reg.setAttribute("msg", "Plane " + msg + " rented successfully!");
		return getData(reg, reg.getUserPrincipal());
	}

	@PostMapping("/generateReport")
	public void generateReport(HttpServletRequest req, HttpServletResponse resp, @RequestParam("airlineId") int id)
			throws JRException, ServletException, IOException {
		byte[] pdf = as.getAirlineReport(id);
		resp.setContentType("application/pdf");
		resp.setHeader("Content-disposition", "attachment; filename=report.pdf");
		resp.setContentLength(pdf.length);
		// Write PDF content to response output stream
	    ServletOutputStream out = resp.getOutputStream();
	    out.write(pdf);
	    out.close();
	}

	@GetMapping("/add-plane")
	public String addPlane(HttpServletRequest req) {
		req.setAttribute("plane", true);
		req.setAttribute("tipovi", as.getTipAvion());
		return "configAirline";
	}

	@GetMapping("/add-pilot")
	public String addPilot(HttpServletRequest req) {
		req.setAttribute("pilot", true);
		return "configAirline";
	}

	@GetMapping("/add-flight")
	public String addFlight(HttpServletRequest req, Principal p) {
		req.setAttribute("flight", true);
		req.setAttribute("avioni", as.getAirplanes(as.getAirline(us.findByUsername(p.getName()).getEmail()).getId()));
		req.setAttribute("airports", bs.getAllAerodrom());
		return "configAirline";
	}
	
	@GetMapping("/add-seats")
	public String addSeats(HttpServletRequest req, Principal p) {
		req.setAttribute("seats", true);
		req.setAttribute("flights", as.getFlights(as.getAirline(us.findByUsername(p.getName()).getEmail()).getId()));
		return "configAirline";
	}

	@PostMapping("/add-plane-confirm")
	public String addPlaneConfirm(HttpServletRequest req, @RequestParam("tailNumber") String tailNumber,
			@RequestParam("tipAviona") int typeId, Principal p) {
		if (tailNumber == null || tailNumber.equals("") || typeId == 0) {
			req.setAttribute("plane", true);
			req.setAttribute("tipovi", as.getTipAvion());
			req.setAttribute("msg", "All fields are required!");
			return "configAirline";
		}
		String id = as.addPlane(tailNumber, typeId, as.getAirline(us.findByUsername(p.getName()).getEmail()).getId());
		if (id == null) {
			req.setAttribute("plane", true);
			req.setAttribute("tipovi", as.getTipAvion());
			req.setAttribute("msg", "Plane with that tail number already exists!");
			return "configAirline";
		}
		getData(req, p);
		req.setAttribute("msg", "Plane added successfully! Tail number is: " + id);
		return "airlineDet";
	}

	@PostMapping("/add-pilot-confirm")
	public String addPilotConfirm(HttpServletRequest req, @RequestParam("name") String name,
			@RequestParam("lastName") String surname, Principal p) {
		if (name == null || name.equals("") || surname == null || surname.equals("")) {
			req.setAttribute("pilot", true);
			req.setAttribute("msg", "All fields are required!");
			return "configAirline";
		}
		String id = as.addPilot(name, surname, as.getAirline(us.findByUsername(p.getName()).getEmail()).getId());
		if (id == null) {
			req.setAttribute("pilot", true);
			req.setAttribute("msg", "Pilot with that email already exists!");
			return "configAirline";
		}
		getData(req, p);
		req.setAttribute("msg", "Pilot added successfully! Pilot is: " + id);
		return "airlineDet";
	}

	@PostMapping("/add-flight-confirm")
	public String addFlightConfirm(HttpServletRequest req, @RequestParam("departureAirport") int depId,
			@RequestParam("arrivalAirport") int arrId, @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME) LocalDateTime dat,
			@RequestParam("airplane") String airplane, @RequestParam("fNumber") String fNumber, Principal p) {
		if (depId == 0 || arrId == 0 || dat == null || dat.equals("") || airplane == null || airplane.equals("")
                || fNumber == null || fNumber.equals("")) {
			req.setAttribute("flight", true);
			req.setAttribute("avioni", as.getAirplanes(as.getAirline(us.findByUsername(p.getName()).getEmail()).getId()));
			req.setAttribute("airports", bs.getAllAerodrom());
			req.setAttribute("msg", "All fields are required!");
			return "configAirline";
		}
		Date d = Date.from(dat.atZone(java.time.ZoneId.systemDefault()).toInstant());
		String id = as.addFlight(depId, arrId, d, airplane, fNumber, as.getAirline(us.findByUsername(p.getName()).getEmail()));
		if (id == null) {
			req.setAttribute("flight", true);
			req.setAttribute("avioni",
					as.getAirplanes(as.getAirline(us.findByUsername(p.getName()).getEmail()).getId()));
			req.setAttribute("airports", bs.getAllAerodrom());
			req.setAttribute("msg", "Flight with that number already exists!");
			return "configAirline";
		}
		getData(req, p);
		req.setAttribute("msg", "Flight added successfully! Flight number is: " + id);
		return "airlineDet";
	}
	
	@PostMapping("/add-seats-confirm")
	public String addSeatsConfirm(@RequestParam("fNumber") String fNumber, @RequestParam("rows") int rows, @RequestParam("columns") int columns, HttpServletRequest req) {
		if (fNumber == null || fNumber.equals("") || rows == 0 || columns == 0) {
			req.setAttribute("seats", true);
			req.setAttribute("flights", as
					.getFlights(as.getAirline(us.findByUsername(req.getUserPrincipal().getName()).getEmail()).getId()));
			req.setAttribute("msg", "All fields are required!");
			return "configAirline";
		}
		int msg = as.addSeats(fNumber, rows, columns);
		if (msg == -1) {
			req.setAttribute("seats", true);
			req.setAttribute("flights", as
					.getFlights(as.getAirline(us.findByUsername(req.getUserPrincipal().getName()).getEmail()).getId()));
			req.setAttribute("msg", "Seats for that flight already exist!");
			return "configAirline";
		}
		getData(req, req.getUserPrincipal());
		req.setAttribute("msg", "Seats added successfully! Total seats added: " + msg);
		return "airlineDet";
	}

}
