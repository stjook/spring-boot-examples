package com.stjook.sbexamples.service;

import com.stjook.sbexamples.model.Employee;

import java.util.List;

/**
 * Created by spournar on 24/5/2017.
 */
public interface EmployeeService {

	Employee load(long employeeId);

	Employee add(Employee employee);

	Employee update(Employee employee);

	List<Employee> findAll();

	void delete(Employee employee);

	void deleteAll();

	boolean isEmployeeExist(long socialSecurity);
}
