package com.stjook.sbexamples.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException() {
		super("Could not find the requested Employee");
	}
}
