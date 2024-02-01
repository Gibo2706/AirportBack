package com.airport.control;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.airport.response.body.SeatSelectionRequest;
import com.airport.response.body.SedisteJson;
import com.airport.service.BookService;

import jakarta.servlet.http.HttpServletRequest;
import model.Karta;
import model.Let;
import model.Sediste;

@Controller
@RequestMapping("/booking/")
public class BookingController {

	@Autowired
	BookService bs;

	@GetMapping("/getData")
	public String getData(HttpServletRequest req) {
		req.getSession().setAttribute("airports", bs.getAllAerodrom());
		return "booking";
	}

	@PostMapping("/showFlights")
	public String getFlights(@RequestParam("departureAirport") int idDep, @RequestParam("arrivalAirport") int idArr,
			@RequestParam("departureDate") String dat, HttpServletRequest req) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateS = null;
		try {
			dateS = dateFormat.parse(dat);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(dateS);
		c.add(Calendar.DATE, 1);
		Date dateE = c.getTime();
		List<Let> letovi = bs.getFlights(dateS, dateE, idDep, idArr);
		req.setAttribute("letovi", letovi);
		return "booking";
	}

	@PostMapping("/seatSelection")
	public String seatSelection(@RequestParam("selectedFlight") String fNumber, HttpServletRequest req) {
		req.setAttribute("sedista", bs.getSeatsForFlight(fNumber));
		req.setAttribute("fNumber", fNumber);
		return "seatSelect";
	}

	@PostMapping("/confirmSelection")
	public String confirmSelection(@RequestBody SeatSelectionRequest seatSelectionBody, HttpServletRequest req,
			Principal p) {
		List<SedisteJson> sedistaRaw = seatSelectionBody.getSelectedSeats();
		List<Sediste> sedista = new LinkedList<>();
		for (SedisteJson sj : sedistaRaw) {
			String[] seats = sj.getSeat().split(" ");
			for (int i = 0; i < seats.length; i++) {
				String[] rowColumn = seats[i].split("-");
				sedista.add(bs.findById(Integer.parseInt(rowColumn[0]), Integer.parseInt(rowColumn[1])));
			}
		}
		req.getSession().setAttribute("fNumber", seatSelectionBody.getFlightNumber());
		req.setAttribute("numberSeats", sedista.size());
		req.setAttribute("pass", p.getName());
		req.getSession().setAttribute("sedista", sedista);
		return "confirmSelection";
	}

	@PostMapping("/confirmReservation")
	public String confirmReservation(@RequestParam("fNumber") String fNumber, HttpServletRequest req, Principal p) {
		@SuppressWarnings("unchecked")
		List<Sediste> sed = (List<Sediste>) (req.getSession().getAttribute("sedista"));
		List<Karta> kar = new LinkedList<>();
		for (Sediste s : sed) {
			kar.add(bs.makeReservation(fNumber, s, p.getName()));
		}

		req.setAttribute("karte", kar);
		req.getSession().removeAttribute("fNumber");
		req.getSession().removeAttribute("sedista");
		return "reservationPreview";
	}
}
