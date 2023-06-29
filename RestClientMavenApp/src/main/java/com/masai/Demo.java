package com.masai;

import org.springframework.web.client.RestTemplate;

public class Demo {
	
	public static void main(String[] args) {
		
		
		RestTemplate rt= new RestTemplate();
		
		EmployeeBean result= rt.getForObject("http://localhost:8080/employees/102", EmployeeBean.class);
		
		System.out.println(result.getName());
		System.out.println(result.getAddress());
		
	}

}
