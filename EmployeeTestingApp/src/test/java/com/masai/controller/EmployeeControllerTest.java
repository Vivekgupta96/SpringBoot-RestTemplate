package com.masai.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masai.model.Employee;
import com.masai.service.EmployeeService;

@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	private Employee requestemployee;
	private Employee responseemployee;

	@BeforeEach
	public void init() {

		requestemployee = new Employee();
		requestemployee.setName("Ram");
		requestemployee.setAddress("delhi");
		requestemployee.setSalary(80000);

		responseemployee = new Employee();
		responseemployee.setEmployeeId(10);
		responseemployee.setName("Ram");
		responseemployee.setAddress("delhi");
		responseemployee.setSalary(80000);

	}

	@Test
	@DisplayName("Employee can be created")
	public void testRegisterEmployeeHandler_whenValidDetailsProvided_returnRegisteredEmployee() throws Exception {

		// Arrange
		Mockito.when(employeeService.registerEmployee(any(Employee.class))).thenReturn(responseemployee);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employees")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(requestemployee));

		// Act
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		String responseBodyAsString = mvcResult.getResponse().getContentAsString();

		Employee createdEmployee = new ObjectMapper().readValue(responseBodyAsString, Employee.class);

		// Assert
		assertEquals(responseemployee.getName(), createdEmployee.getName(),
				"returned created Employee Name is incorrect");

		assertNotNull(createdEmployee.getEmployeeId(), "created Customer id should not be empty");

	}

	@Test
	@DisplayName("name size should be min 3 and max 10 charecters")
	void testCreateCustomer_whenFirstNameIsOnlyOneCharecter_returns400StatusCode() throws Exception {

		// Arrange

		Mockito.when(employeeService.registerEmployee(any(Employee.class))).thenReturn(responseemployee);
		requestemployee.setName("R"); // lets provide invalid Name

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employees")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(requestemployee));

		// Act
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus(),
				"Http Status code is not set to 400");

	}

}
