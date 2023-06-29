package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.EmployeeException;
import com.masai.model.Employee;
import com.masai.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Override
	public Employee registerEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
		
		
	}

	@Override
	public Employee getEmployeeById(Integer empId) throws EmployeeException {
		
		
		Optional<Employee> opt= employeeRepository.findById(empId);
		
		if(opt.isPresent())
			return opt.get();
		else
			throw new EmployeeException("Employee does not exist with Id: "+empId);
		
		
		
	}

	@Override
	public List<Employee> getAllEmployeeDetails() throws EmployeeException {
		
	 	List<Employee> employees= employeeRepository.findAll();
		
	 	if(employees.isEmpty())
	 		throw new EmployeeException("No Employee found..");
	 	else
	 		return employees;
	 	
	 	
	}
}