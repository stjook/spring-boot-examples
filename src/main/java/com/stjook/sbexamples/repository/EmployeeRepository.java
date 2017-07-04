package com.stjook.sbexamples.repository;

import com.stjook.sbexamples.model.Employee;

import java.util.List;

/**
 * Created by spournar on 24/5/2017.
 */
public interface EmployeeRepository {

	List<Employee> getAllEmployees();

	Employee getEmployeeById(long employeeId);

	void addEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployee(long employeeId);

	void deleteAllEmployees();

	boolean isEmployeeExists(long socialSecurity);
}
