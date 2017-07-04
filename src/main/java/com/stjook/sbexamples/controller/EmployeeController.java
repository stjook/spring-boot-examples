package com.stjook.sbexamples.controller;

import com.stjook.sbexamples.model.Employee;
import com.stjook.sbexamples.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * Created by spournar on 24/5/2017.
 */
@RestController
@RequestMapping("api")
public class EmployeeController {

	private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);


	@Autowired
	EmployeeService service;

	// Load employee.
	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
	public ResponseEntity<Employee> load(
			@PathVariable @NotNull Long employeeId) {
		Employee employee = service.load(employeeId);
		return new ResponseEntity(employee, new HttpHeaders(), HttpStatus.OK);
	}

	// Create employee.
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<Void> create(
			@RequestBody Employee employee) {
		if (service.isEmployeeExists(employee.getSocialSecurity())) {
			System.out.println("An Employee with social security " + employee.getSocialSecurity() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		service.add(employee);
		return new ResponseEntity("Employee has been created", new HttpHeaders(), HttpStatus.CREATED);
	}

	// Update employee.
	@RequestMapping(value = "/employee/{employeeId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<Employee> update(
			@PathVariable @NotNull Long employeeId,
			@RequestBody Employee employee) {
		service.update(employee);
		return new ResponseEntity("Employee has been updated", new HttpHeaders(), HttpStatus.CREATED);
	}

	// Delete employee.
	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> delete(
			@PathVariable @NotNull Long employeeId) {
		service.delete(employeeId);
		return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
	}

    // Delete all employees.
    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteAll() {
		service.deleteAll();
	    return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }

	// Load all employees.
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ResponseEntity<Collection<Employee>> list() {
		List<Employee> employees = service.findAll();
		return new ResponseEntity(employees, new HttpHeaders(), HttpStatus.OK);
	}

}
