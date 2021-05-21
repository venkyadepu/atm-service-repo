package com.mobiquity.atm.exception;

public class RootException extends RuntimeException{
	
	private String errorCode;

	private String description;

	public RootException(String errorCode, String description) {
		this.errorCode = errorCode;
		this.description = description;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getDescription() {
		return description;
	}

}
