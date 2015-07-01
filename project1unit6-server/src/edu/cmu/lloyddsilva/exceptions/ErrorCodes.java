package edu.cmu.lloyddsilva.exceptions;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public enum ErrorCodes {

	FILE_NOT_FOUND(101, "The filename was not found. Please enter a valid filename."),
	BASE_PRICE_NOT_FOUND(201, "The base price for the auto was not found. Please enter a valid base price.");

	private final int code;
	private final String description;

	private ErrorCodes(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + "::" + description;
	}
}
