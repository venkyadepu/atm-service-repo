package com.mobiquity.atm.service;

import java.util.List;

import com.mobiquity.atm.response.AtmResponse;

public interface AtmService {
	
	List<AtmResponse> findAllAtms();
	
	List<AtmResponse> findAtmsByCity(String city);

}
