package com.masai;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientBean {

	
	@Autowired
	private RestTemplate rt;
	
	
	public void callingApi() {
		
		
		//RestTemplate rt= new RestTemplate();
		
		
		
		EmployeeBean emp= new EmployeeBean();
		emp.setName("Ratan");
		emp.setAddress("Mumbai");
		emp.setSalary(89000);
		
		ResponseEntity<EmployeeBean> re= rt.postForEntity("http://localhost:8080/employees", emp, EmployeeBean.class);
		//here automatically the matching fileds will be populated			

		//to get the actual response data (response body)
		EmployeeBean registerdEmployee= re.getBody();
		
		System.out.println(registerdEmployee);
		
		System.out.println("the status code is "+re.getStatusCode());
		
	 	HttpHeaders hh= re.getHeaders();
		
	 	Set set= hh.entrySet();
	 	
	 	System.out.println("Response Headers are :");
	 	System.out.println(set);
	 	
		
		
		
		
	}
	
	
}
