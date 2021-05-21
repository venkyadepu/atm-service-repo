package com.mobiquity.atm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobiquity.atm.response.AtmResponse;
import com.mobiquity.atm.service.AtmService;

@RestController
@RequestMapping("/api/v1/")
public class AtmController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AtmController.class);

	private final AtmService atmService;

	@Autowired
	public AtmController(AtmService atmService) {
		this.atmService = atmService;
	}

	@GetMapping(value="allAtms",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AtmResponse>> findAllAtms() {
		LOGGER.info("Received input request to get all the Atms");
		return ResponseEntity.ok(atmService.findAllAtms());
	}

	@GetMapping(value="findAtmsByCity/{city}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AtmResponse>> findAtmsByCity(@PathVariable final String city) {
		LOGGER.info("Atms by city {} ", city);
		return ResponseEntity.ok(atmService.findAtmsByCity(city));
	}

}
