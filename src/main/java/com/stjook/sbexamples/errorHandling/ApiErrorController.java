package com.stjook.sbexamples.errorHandling;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * This is the com.openbet.games.admin.config.controller for the /error path. There are two main scenarios where this com.openbet.games.admin.config.controller is used:
 *
 * <li>Errors processed by the spring framework (such as unauthorised or page not found)
 * <li>Exceptions that don't have explicit handlers defined in {@link ResponseRestExceptionHandler}
 *
 */
@Controller
@RequestMapping("${error.path:/error}")
public class ApiErrorController implements ErrorController {

	@Value("${error.path:/error}")
	private String errorPath;

	@Override
	public String getErrorPath() {
		return this.errorPath;
	}

	@RequestMapping
	@ResponseBody
	public ResponseEntity<Object> error(HttpServletRequest request) {
		HttpStatus status = getStatus(request);

		final ApiError apiError = new ApiError(status, status.getReasonPhrase(),
				getMessage(request, status.getReasonPhrase()));
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), status);
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}

	private String getMessage(HttpServletRequest request, String defaultMsg) {
		String message = (String) request.getAttribute("javax.servlet.error.message");
		if (message == null || message == "") {
			return defaultMsg;
		}
		return message;
	}


}