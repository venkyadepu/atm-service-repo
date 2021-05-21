package com.mobiquity.atm.exception;

public class AtmNotFoundException extends RootException{
	
	public AtmNotFoundException(String errorCode, String description) {
		super(errorCode,description);
	}


}
