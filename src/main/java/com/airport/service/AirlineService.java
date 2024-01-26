package com.airport.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.repo.AvioRepo;
import com.airport.repo.AvionRepo;
import com.airport.repo.BookRepo;
import com.airport.repo.KartaRepo;
import com.airport.repo.PilotRepo;

import model.Aviokompanija;
import model.Avion;
import model.Karta;
import model.Let;
import model.Pilot;
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

	public Aviokompanija getAirline(String email) {
		return avior.findByEmail(email);
	}

	public List<Pilot> getPilots(String airline) {
		return pr.findByAviokompanija(airline);
	}

	public List<Avion> getAirplanes(int airlineId) {
		return ar.findByAviokompanija(airlineId);
	}

	public boolean rentAirplane(String tailNumber, int airlineId) {
		Avion a = ar.findById(tailNumber).get();
		if (a.getAviokompanija2() == null) {
			a.setAviokompanija2(avior.findById(airlineId).get());
			ar.save(a);
			return true;
		}
		return false;
	}

	public List<Let> getFlights(int id) {
		return br.findByAvioKompanija(id);
	}

	public byte[] getAirlineReport(int id) throws JRException {
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
		report = JasperExportManager.exportReportToPdf(jp);
		return report;
	}

}
