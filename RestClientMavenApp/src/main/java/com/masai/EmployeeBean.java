package com.masai;


//client domain class 
public class EmployeeBean {
	
	
	private Integer employeeId;
	private String name;
	private String address;
	private Integer salary;
	
	
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
		return "EmployeeBean [employeeId=" + employeeId + ", name=" + name + ", address=" + address + ", salary="
				+ salary + "]";
	}
	
	
	

}
