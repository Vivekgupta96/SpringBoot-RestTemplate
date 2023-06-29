package com.masai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer employeeId;

	@Size(min = 3, max = 10, message = "Name Length should be min 3 and max 10")
	@NotNull(message = "name is mandatory")
	private String name;

	@NotNull(message = "Address is mandatory")
	private String address;

	@NotNull(message = "Salary is mandatory")
	@Min(value = 10000, message = "Salary should be minimum 10000")
	@Max(value = 100000, message = "Salary should be maximum 100000")
	private Integer salary;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(Integer employeeId, String name, String address, Integer salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
		this.salary = salary;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", address=" + address + ", salary=" + salary
				+ "]";
	}

}