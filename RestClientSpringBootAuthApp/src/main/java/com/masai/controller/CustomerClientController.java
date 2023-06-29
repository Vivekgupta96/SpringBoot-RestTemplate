package com.masai.controller;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.masai.model.Customer;
import com.masai.model.LoginBean;

@RestController
public class CustomerClientController {

@PostMapping("/getAllCustomers")
	public List<Customer> signInCustomer(@RequestBody LoginBean bean) {
			
	//it can be used inside the Spring Boot application only, not inside the normal maven application
		RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate rt= builder.basicAuthentication(bean.getUsername(),bean.getPassword()).build();
		
		String url = "http://localhost:8080/signIn";
		
		ResponseEntity<String> re= rt.getForEntity(url, String.class);
		
		System.out.println("Loggin Message is :"+re.getBody());
		
		String token= re.getHeaders().getFirst("Authorization");
		
		System.out.println("Jwt Token is:  "+token);
		
		
		//protected API to call, we need to pass the jwt   token
		String url2="http://localhost:8080/customers";
		
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization", "Bearer "+token);
		
		
		
		HttpEntity<String> he = new HttpEntity<>(headers); 
		
		//ResponseEntity<List> re= rt2.exchange(url2,HttpMethod.GET ,he,List.class);
		
ResponseEntity<List<Customer>> re2= rt.exchange(url2,HttpMethod.GET ,he,new ParameterizedTypeReference<List<Customer>>() {});
		
		System.out.println(re2.getBody());
			
		return re2.getBody();
}
}