package com.mobiquity.atm.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.atm.exception.AtmNotFoundException;
import com.mobiquity.atm.exception.RootException;
import com.mobiquity.atm.response.AtmResponse;

@Service
public class AtmServiceImpl implements AtmService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AtmServiceImpl.class);

	private final RestTemplate restTemplate;
	
	
	private final String serviceUrl;

	@Autowired
	public AtmServiceImpl(RestTemplate restTemplate,@Value("${atms.extrenal.service.url}") String serviceUrl) {
		this.serviceUrl =serviceUrl;
		this.restTemplate = restTemplate;
	}
	
	/**
	 * @author venkatesh
	 * Get All the ATMs
	 */

	@Override
	public List<AtmResponse> findAllAtms() {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(serviceUrl, HttpMethod.GET, entity,
					String.class);
		} catch (Exception e) {
			LOGGER.error("Unable to connect the external Service");
			throw new RootException("ATM_SERVICE_ERROR", "Unable to Connect the External Service");
		}

		if (response.getBody() == null) {
			LOGGER.error("No Atms found");
			throw new AtmNotFoundException("ATM_SERVICE_DETAILS_NOT_FOUND", "No Atms Found");
		}
		
		return convertToListOfAtmResponseDto(response.getBody());
	}
	
	/**
	 * @author venkatesh
	 * @param city
	 * Get the ATMS based on the city
	 */

	@Override
	public List<AtmResponse> findAtmsByCity(String city) {
		LOGGER.info(" First get all the Atms");
		List<AtmResponse> atmResponses = findAllAtms();
		LOGGER.info("Filtering the Atms by city");
		List<AtmResponse> filteredResponse = atmResponses.stream()
				.filter(atmResponse -> atmResponse.getAddress().getCity().equals(city)).collect(Collectors.toList());
		if(filteredResponse==null || filteredResponse.isEmpty()) {
			LOGGER.error("No Atms found in city : {}",city);
			throw new AtmNotFoundException("ATM_SERVICE_DETAILS_NOT_FOUND", "No Atms found in city : "+city);
		}
		return filteredResponse;
	}
	
	private List<AtmResponse> convertToListOfAtmResponseDto(String responseFromService) {

		ObjectMapper mapper = new ObjectMapper();
		String[] correctJson = responseFromService.split("',");
		AtmResponse[] atmResponses = null;
		try {
			atmResponses = mapper.readValue(correctJson[1], AtmResponse[].class);
		} catch (JsonProcessingException e) {
			throw new RootException("ATM_SERVICE_ERROR", "Cannot deserialize the response");
		}
		return Arrays.asList(atmResponses);
	}

}
