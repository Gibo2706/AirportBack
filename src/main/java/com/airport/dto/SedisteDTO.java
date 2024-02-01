package com.airport.dto;

public class SedisteDTO {
	private int row;
	private int column;

	public SedisteDTO() {
	}

	public SedisteDTO(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public int setRow(int row) {
		return this.row = row;
	}

	public int setColumn(int column) {
		return this.column = column;
	}

}
