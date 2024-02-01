package com.airport.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.repo.AerodromRepo;
import com.airport.repo.AvioRepo;
import com.airport.repo.AvionRepo;
import com.airport.repo.BookRepo;
import com.airport.repo.KartaRepo;
import com.airport.repo.PilotRepo;
import com.airport.repo.SedisteRepo;
import com.airport.repo.TipavionaRepo;

import model.Aviokompanija;
import model.Avion;
import model.Karta;
import model.Let;
import model.Pilot;
import model.Sediste;
import model.SedistePK;
import model.Tipaviona;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class AirlineService {
	@Autowired
	private AvionRepo ar;

	@Autowired
	private AvioRepo avior;

	@Autowired
	private PilotRepo pr;

	@Autowired
	private BookRepo br;

	@Autowired
	private KartaRepo kr;

	@Autowired
	private AerodromRepo aeror;

	@Autowired
	private TipavionaRepo tar;
	
	@Autowired
	private SedisteRepo sr;

	public Aviokompanija getAirline(String email) {
		return avior.findByEmail(email);
	}

	public List<Pilot> getPilots(String airline) {
		return pr.findByAviokompanija(airline);
	}

	public List<Avion> getAirplanes(int airlineId) {
		return ar.findByAviokompanija(airlineId);
	}

	public List<Tipaviona> getTipAvion() {
		return tar.findAll();
	}

	public String rentAirplane(String tailNumber, int airlineId) {
		Avion a = ar.findById(tailNumber).get();
		a.setAviokompanija2(avior.findById(airlineId).get());
		a = ar.save(a);
		return a.getTailNumber();
	}

	public List<Let> getFlights(int id) {
		return br.findByAvioKompanija(id);
	}

	public byte[] getAirlineReport(int id) throws JRException, IOException {
		Aviokompanija a = avior.findById(id).orElse(null);
		if (a == null) {
			throw new RuntimeException("Airline not found!");
		}

		List<Karta> karte = kr.findAllByAirline(id);
		if (karte.isEmpty()) {
			throw new RuntimeException("No tickets found for the airline!");
		}

		byte[] report = null;
		InputStream in = this.getClass().getResourceAsStream("/jasperreports/karteIzvestaj.jrxml");
		if (in == null) {
			throw new RuntimeException("JRXML file not found!");
		}

		JasperReport jr = JasperCompileManager.compileReport(in);
		Map<String, Object> params = new HashMap<>();
		params.put("avio", a.getNaziv());

		JasperPrint jp = JasperFillManager.fillReport(jr, params, new JRBeanCollectionDataSource(karte));
		in.close();
		report = JasperExportManager.exportReportToPdf(jp);
		return report;
	}

	public boolean checkName(String name) {
		if (name != null && !name.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean checkEmail(String email) {
		if (email != null && !email.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public int addAirline(String name, String email) {
		Aviokompanija a = new Aviokompanija();
		a.setNaziv(name);
		a.setEmail(email);
		a = avior.save(a);
		return a.getId();
	}

	public String addPlane(String tailNumber, int typeId, int id) {
		Avion a = new Avion();
		a.setTailNumber(tailNumber);
		a.setTipaviona(tar.findById(typeId).get());
		a.setAviokompanija1(avior.findById(id).get());
		a = ar.save(a);
		return a.getTailNumber();
	}

	public String addPilot(String name, String surname, int id) {
		Pilot p = new Pilot();
		p.setName(name);
		p.setSurname(surname);
		p.setAviokompanija(avior.findById(id).get());
		p = pr.save(p);
		return p.getName() + " " + p.getSurname();
	}

	public String addFlight(int depId, int arrId, Date dat, String airplane, String fNumber,
			Aviokompanija aviokompanija) {
		Let l = new Let();
		l.setAerodrom1(aeror.findById(depId).get());
		l.setAerodrom2(aeror.findById(arrId).get());
		l.setDatum(dat);
		l.setAvion(ar.findById(airplane).get());
		l.setfNumber(fNumber);
		l.setAviokompanija(aviokompanija);

		l = br.save(l);

		return l.getfNumber();
	}

	public List<Avion> getRentedAirplanes(int id) {
		return ar.findByAviokompanija2(id);
	}

	public int addSeats(String fNumber, int rows, int columns) {
		Let l = br.findById(fNumber).get();
		if (l == null) {
			return -1;
		}
		int seats = rows * columns;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Sediste s = new Sediste();
				SedistePK pk = new SedistePK();
				pk.setRedovi(i+ 1);
				pk.setKolone(j + 1);
				s.setId(pk);
				try {
					sr.save(s);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		return seats;
	}

}
