package com.stjook.sbexamples.model;


import javax.persistence.*;

/**
 * Created by spournar on 24/5/2017.
 */
@Entity
@Table(name = "tEmployee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private long employeeId;

	@Column(name = "social_security")
	private long socialSecurity;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "salary")
	private double salary;


	public Employee(long socialSecurity, String firstName, String lastName, double salary) {
		this.socialSecurity = socialSecurity;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}

	public Employee(long employeeId) {
		this.employeeId = employeeId;
		this.socialSecurity = getSocialSecurity();
		this.firstName = getFirstName();
		this.lastName = getLastName();
		this.salary = getSalary();
	}


	Employee() { // jpa only
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(long socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"employeeId=" + employeeId +
				", socialSecurity=" + socialSecurity +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", salary=" + salary +
				'}';
	}
}
