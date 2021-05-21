package com.mobiquity.atm.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mobiquity.atm.model.Address;
import com.mobiquity.atm.response.AtmResponse;
import com.mobiquity.atm.service.AtmService;

@ExtendWith(MockitoExtension.class)
public class AtmControllerTest {

	@Mock
	private AtmService atmService;

	@InjectMocks
	private AtmController atmController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(atmController).build();
	}

	@Test
	public void testFindAllAtms() throws Exception {

		AtmResponse atmResponse = new AtmResponse();
		Address address = new Address();
		address.setCity("Leeuwarden");
		atmResponse.setAddress(address);

		when(atmService.findAllAtms()).thenReturn(Arrays.asList(atmResponse));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/allAtms").contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print());
		
		verify(atmService).findAllAtms();
		verify(atmService, times(1)).findAllAtms();
	}
	
	@Test
	public void testFindAtmsByCity() throws Exception {

		AtmResponse atmResponse = new AtmResponse();
		Address address = new Address();
		address.setCity("Leeuwarden");
		atmResponse.setAddress(address);

		when(atmService.findAtmsByCity(Mockito.anyString())).thenReturn(Arrays.asList(atmResponse));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/findAtmsByCity/Leeuwarden").contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print());
		
		verify(atmService).findAtmsByCity(Mockito.anyString());
		verify(atmService, times(1)).findAtmsByCity(Mockito.anyString());
	}

}
