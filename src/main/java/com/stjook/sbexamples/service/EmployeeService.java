package com.stjook.sbexamples.service;


import com.stjook.sbexamples.model.Employee;
import com.stjook.sbexamples.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by spournar on 24/5/2017.
 */
@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public Employee load(long employeeId) {
		return repository.getEmployeeById(employeeId);
	}

	public void add(Employee employee) {
		repository.addEmployee(employee);
	}

	public void update(Employee employee) {
		repository.updateEmployee(employee);
	}

	public List<Employee> findAll() {
		return repository.getAllEmployees();
	}

	public void delete(long employeeId) {
		repository.deleteEmployee(employeeId);
	}

	public void deleteAll() {
		repository.deleteAllEmployees();
	}

	public boolean isEmployeeExists(long socialSecurity) {
		return repository.isEmployeeExists(socialSecurity);
	}
}
