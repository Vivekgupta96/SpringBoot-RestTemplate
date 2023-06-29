package com.masai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.masai.model.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "/application-test.properties")
class EmployeeTestingAppApplicationTests {

	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@DisplayName("Employee can be created")
	public void testRegisterEmployeeHandler_whenValidDetailsProvided_returnRegisteredEmployee() throws Exception {

		//Arrange
		Employee employee = new Employee();
		employee.setName("Vikash");
		employee.setAddress("delhi");
		employee.setSalary(90000);
		
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Employee> entity= new HttpEntity<Employee>(employee,headers);
		
		//Act
		ResponseEntity<Employee> re= testRestTemplate.postForEntity("/employees", entity, Employee.class);
		
		Employee createdEmployee= re.getBody();
		
		
		//Assert
		assertEquals(HttpStatus.CREATED, re.getStatusCode(), "Created Status code is not 201");
		assertNotNull(createdEmployee.getEmployeeId(), "Registed Employee should have an Id");
		
		
	
	
	}

}
