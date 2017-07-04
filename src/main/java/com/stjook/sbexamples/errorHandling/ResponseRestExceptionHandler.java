package com.stjook.sbexamples.errorHandling;



import com.stjook.sbexamples.exceptions.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResponseRestExceptionHandler extends ResponseEntityExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(ResponseRestExceptionHandler.class);

	@ExceptionHandler({ EmployeeNotFoundException.class })
	public ResponseEntity<Object> handleItemNotFoundException(final EmployeeNotFoundException ex, final WebRequest request) {
		logger.info(ex.getClass().getName(), ex);

		final String errorMsg = ex.getLocalizedMessage();

		final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, errorMsg, errorMsg);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
		logger.info(ex.getClass().getName());

		final List<String> errors = ex.getConstraintViolations().stream()
				.map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
				.collect(Collectors.toList());

		final String errorMsg = errors.stream().collect(Collectors.joining(", "));

		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errorMsg, errors);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// Catch and log any unhandled exceptions.
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleExceptionInternal(final Exception ex) {
		logger.info(ex.getClass().getName(), ex);

		final HttpStatus errorHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		final String errorMsg = errorHttpStatus.getReasonPhrase();

		final ApiError apiError = new ApiError(errorHttpStatus, errorMsg, errorMsg);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}

