package com.masai.service;

import java.util.List;

import com.masai.exception.EmployeeException;
import com.masai.model.Employee;

public interface EmployeeService {

public Employee registerEmployee(Employee employee);
	
	public Employee getEmployeeById(Integer empId)throws EmployeeException;

	public List<Employee> getAllEmployeeDetails()throws EmployeeException;
}
