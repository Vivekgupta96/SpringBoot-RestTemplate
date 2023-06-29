package com.masai;

import java.util.Base64;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientBean {

	public void callingApi() {

		RestTemplate rt= new RestTemplate();
		
		String authStr = "ram@gmail.com:1234";
		String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);

		// shortcut approach
		// headers.setBasicAuth("ram@gmail.com", "1234");

		HttpEntity<String> request = new HttpEntity<>(headers);
		//Here String represents request body data. but here it will be null
		

		ResponseEntity<String> response = rt.exchange("http://localhost:8080/signIn", HttpMethod.GET, request,
				String.class);

		//getting the body
		String result = response.getBody();
		System.out.println(result);

		
		//getting the token
		String token = response.getHeaders().getFirst("Authorization");
		System.out.println(token);

		
		//creating another headers object to call another api
		HttpHeaders headers2 = new HttpHeaders();
		
		//attaching the jwt token to the another protected api call
		//headers2.add("Authorization", "Bearer " + token);

		// shortcut approach
		headers2.setBearerAuth(token);

		HttpEntity<String> he = new HttpEntity<>(headers2);

	 //ResponseEntity<List> re2= rt.exchange("http://localhost:8080/customers",HttpMethod.GET ,he,List.class);

		ResponseEntity<List<Customer>> re2 = rt
											.exchange("http://localhost:8080/customers", 
													HttpMethod.GET, he, 
													new ParameterizedTypeReference<List<Customer>>() {});

		List<Customer> customers= re2.getBody();
		
		customers.forEach(c -> System.out.println(c));

	}

}
