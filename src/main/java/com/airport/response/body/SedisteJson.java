package com.airport.response.body;

public class SedisteJson {
	private String seat;
	
	public SedisteJson() {
    }

    public SedisteJson(String id) {
        this.seat = id;
    }

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}
}