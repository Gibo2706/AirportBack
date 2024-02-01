package com.airport.dto;

import model.Korisnik;
import model.Let;

public class KartaDTO {
	private int id;
	private Korisnik korisnikBean;
	private Let let;
	private SedisteDTO sediste;

	public KartaDTO() {
		super();
	}

	public KartaDTO(int id, Korisnik korisnikBean, Let let, SedisteDTO sediste) {
		super();
		this.id = id;
		this.korisnikBean = korisnikBean;
		this.let = let;
		this.sediste = sediste;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Korisnik getKorisnikBean() {
		return korisnikBean;
	}

	public void setKorisnikBean(Korisnik korisnikBean) {
		this.korisnikBean = korisnikBean;
	}

	public Let getLet() {
		return let;
	}

	public void setLet(Let let) {
		this.let = let;
	}

	public SedisteDTO getSediste() {
		return sediste;
	}

	public void setSediste(SedisteDTO sediste) {
		this.sediste = sediste;
	}

}
