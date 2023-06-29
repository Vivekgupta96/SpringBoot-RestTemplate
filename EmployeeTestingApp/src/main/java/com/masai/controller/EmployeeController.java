package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Employee;
import com.masai.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employees")
	public ResponseEntity<Employee> registerEmployeeHandler(@Valid @RequestBody Employee employee) {

		Employee registeredEmployee = employeeService.registerEmployee(employee);

		return new ResponseEntity<>(registeredEmployee, HttpStatus.CREATED);

	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeByIdHandler(@PathVariable("id") Integer empId) {

		Employee employee = employeeService.getEmployeeById(empId);

		return new ResponseEntity<>(employee, HttpStatus.OK);

	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployeeHandler() {

		List<Employee> employees = employeeService.getAllEmployeeDetails();

		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

}