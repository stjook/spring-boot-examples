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
public class ProtectedEmployeeService implements EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public Employee load(long employeeId) {
		return repository.findOne(employeeId);
	}

	public Employee add(Employee employee) {
		return repository.save(employee);
	}

	public Employee update(Employee employee) {
		return repository.save(employee);
	}

	public List<Employee> findAll() {
		return repository.findAll();
	}

	public void delete(Employee employee) {
		repository.delete(employee);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	public boolean isEmployeeExist(long socialSecurity) {
		return repository.existsBySocialSecurity(socialSecurity);
	}
}
