package com.mobiquity.atm.response;

import java.util.List;

import com.mobiquity.atm.model.Address;
import com.mobiquity.atm.model.OpeningHour;

public class AtmResponse {
	
	private Address address;

	private int distance;

	private List<OpeningHour> openingHours;

	private String functionality;

	private String type;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public List<OpeningHour> getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(List<OpeningHour> openingHours) {
		this.openingHours = openingHours;
	}

	public String getFunctionality() {
		return functionality;
	}

	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
