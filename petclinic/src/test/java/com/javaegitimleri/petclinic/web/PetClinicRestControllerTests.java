package com.javaegitimleri.petclinic.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.net.URI;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.javaegitimleri.petclinic.model.Owner;

public class PetClinicRestControllerTests { //rest template kullanarak test edecegız
	private RestTemplate restTemplate;
	
	
	
	@Before 			//setup methodları her test methodları 
	public void setup() { //calısmadan calısır ve ıcındekı kodu execute eder
		restTemplate = new  RestTemplate();
	}

	@Test
	public void testUpdateOwner() {
		Owner owner= restTemplate.getForObject("http://localhost:8080/rest/owner/1", Owner.class);
		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("omer"));
		owner.setFirstName("Omer Teoman");
		restTemplate.put("http://localhost:8080/rest/owner/1", owner);
		owner= restTemplate.getForObject("http://localhost:8080/rest/owner/1", Owner.class);
		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Omer Teoman"));
		
		
		
	}

	
	@Test
	public void testGetOwnerById() {
		ResponseEntity<Owner> response=restTemplate.getForEntity("http://localhost:8080/rest/owner/1",Owner.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("omer"));
	}	
	@Test
	public void testGetByLastName() {
		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owner?ln=uysal", List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		List<Map<String,String>> body = response.getBody();
		List<String> firstNames = body.stream().map(e->e.get("firstName")).collect(Collectors.toList());
		MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("omer","owner2"));
		
	}
	@Test
	public void testGetOwners() {
		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owners", List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		
		List<Map<String,String>> body = response.getBody();
		List<String> firstNames = body.stream().map(e->e.get("firstName")).collect(Collectors.toList());
		MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("omer","owner2","owner3","mert"));
		
	}
	
	@Test
	public void testCreateOwner() {
		Owner owner = new Owner();
		owner.setId(4L);
		owner.setFirstName("ali");
		owner.setLastName("ali");
		URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner",owner);
		Owner owner2=restTemplate.getForObject(location, Owner.class);
		MatcherAssert.assertThat(owner2.getFirstName(), Matchers.equalTo(owner.getFirstName()));//donen owner kaydının bızım
		MatcherAssert.assertThat(owner2.getLastName(), Matchers.equalTo(owner2.getLastName()));// yarattıgımız kayıt olup olmadıgını assert ederız
	}
	
	
	

}
