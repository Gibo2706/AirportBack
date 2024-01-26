package com.airport.control;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.airport.service.AirlineService;
import com.airport.service.UserService;

import jakarta.servlet.ServletException;
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
	private UserService  us;
	


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
		return "airlineDet";
	}
	
	@GetMapping("/rent")
	public String rentAirplane(HttpServletRequest req, Principal p) {
		getData(req, p);
		return "rent";
	}
	
	@PostMapping("/rentPlane")
	public String rentAirplane(HttpServletRequest reg, @RequestParam("tailNumber") String tailNumber, @RequestParam("renter") String rentersEmail) {
        as.rentAirplane(tailNumber, as.getAirline(rentersEmail).getId());
        return "redirect:/avio/details";
    }
	
	@PostMapping("/generateReport")
	public String generateReport(HttpServletRequest req, HttpServletResponse resp, @RequestParam("airlineId") int id) throws JRException, ServletException, IOException {
		byte[] pdf = as.getAirlineReport(id);
		resp.setContentType("application/pdf");
		resp.setHeader("Content-disposition", "attachment; filename=report.pdf");
		resp.setContentLength(pdf.length);
		resp.getOutputStream().write(pdf);
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
		return "redirect:/avio/details";
	}
}
