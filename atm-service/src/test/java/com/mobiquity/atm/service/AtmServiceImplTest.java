package com.mobiquity.atm.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.mobiquity.atm.exception.AtmNotFoundException;

@ExtendWith(MockitoExtension.class)
public class AtmServiceImplTest {

	@InjectMocks
	private AtmServiceImpl atmServiceImpl;

	@Mock
	private RestTemplate restTemplate;

	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(atmServiceImpl, "serviceUrl", "https://www.ing.nl/api/locator/atms/");
	}

	@Test
	public void testFindAllAtms() {
		String response = ")]}',\r\n"
				+ "[{\"address\":{\"street\":\"Emmakade\",\"housenumber\":\"168\",\"postalcode\":\"8933 AX\",\"city\":\"Leeuwarden\",\"geoLocation\":{\"lat\":\"53.199609\",\"lng\":\"5.81608\"}},\"distance\":0,\"openingHours\":[{\"dayOfWeek\":2,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":3,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":4,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":5,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":6,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":7,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":1,\"hours\":[{\"hourFrom\":\"12:00\",\"hourTo\":\"18:00\"}]}],\"functionality\":\"Geldautomaat\",\"type\":\"GELDMAAT\"}]";

		when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class),
				Mockito.any(Class.class))).thenReturn(new ResponseEntity<String>(response, HttpStatus.OK));
		atmServiceImpl.findAllAtms();
	}

	@Test
	public void testFindAllAtmsException() {
		String response = null;

		when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class),
				Mockito.any(Class.class))).thenReturn(new ResponseEntity<String>(response, HttpStatus.OK));
		assertThrows(AtmNotFoundException.class, () -> atmServiceImpl.findAllAtms());
	}

	@Test
	public void testFindAtmsByCity() {
		String response = ")]}',\r\n"
				+ "[{\"address\":{\"street\":\"Emmakade\",\"housenumber\":\"168\",\"postalcode\":\"8933 AX\",\"city\":\"Leeuwarden\",\"geoLocation\":{\"lat\":\"53.199609\",\"lng\":\"5.81608\"}},\"distance\":0,\"openingHours\":[{\"dayOfWeek\":2,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":3,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":4,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":5,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":6,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":7,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":1,\"hours\":[{\"hourFrom\":\"12:00\",\"hourTo\":\"18:00\"}]}],\"functionality\":\"Geldautomaat\",\"type\":\"GELDMAAT\"}]";

		when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class),
				Mockito.any(Class.class))).thenReturn(new ResponseEntity<String>(response, HttpStatus.OK));
		atmServiceImpl.findAtmsByCity("Leeuwarden");
	}

	@Test
	public void testFindAtmsByCityException() {

		String response = ")]}',\r\n"
				+ "[{\"address\":{\"street\":\"Emmakade\",\"housenumber\":\"168\",\"postalcode\":\"8933 AX\",\"city\":\"Leeuwarden\",\"geoLocation\":{\"lat\":\"53.199609\",\"lng\":\"5.81608\"}},\"distance\":0,\"openingHours\":[{\"dayOfWeek\":2,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":3,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":4,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":5,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":6,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":7,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"21:00\"}]},{\"dayOfWeek\":1,\"hours\":[{\"hourFrom\":\"12:00\",\"hourTo\":\"18:00\"}]}],\"functionality\":\"Geldautomaat\",\"type\":\"GELDMAAT\"}]";

		when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class),
				Mockito.any(Class.class))).thenReturn(new ResponseEntity<String>(response, HttpStatus.OK));
		assertThrows(AtmNotFoundException.class, () -> atmServiceImpl.findAtmsByCity("Emmakade"));
	}

}
