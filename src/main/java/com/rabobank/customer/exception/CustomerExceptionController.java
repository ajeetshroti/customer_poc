package com.rabobank.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The Class CustomerExceptionController.
 */
@ControllerAdvice
public class CustomerExceptionController {

	/**
	 * CustomerValidationException.
	 *
	 * @param exception the CustomerValidationException
	 * @return the response entity
	 */
	@ExceptionHandler(value = CustomerValidationException.class)
	public ResponseEntity<Object> exception(CustomerValidationException exception) {
		return new ResponseEntity<>(exception.message, HttpStatus.BAD_REQUEST);
	}

	/**
	 * CustomerDaoException.
	 *
	 * @param exception the CustomerDaoException
	 * @return the response entity
	 */
	@ExceptionHandler(value = CustomerDaoException.class)
	public ResponseEntity<Object> exception(CustomerDaoException exception) {
		return new ResponseEntity<>(exception.message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	/**
	 * CustomerNoContentException.
	 *
	 * @param exception the CustomerNoContentException
	 * @return the response entity
	 */
	@ExceptionHandler(value = CustomerNoContentException.class)
	public ResponseEntity<Object> exception(CustomerNoContentException exception) {
		return new ResponseEntity<>(exception.message, HttpStatus.NO_CONTENT);
	}
}
