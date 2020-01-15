package com.rabobank.customer.exception;

/**
 * The Class CustomerDaoException.
 */
public class CustomerDaoException extends CustomerException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The code. */
	public String code;
	
	/** The message. */
	public String message;

	/**
	 * Instantiates a new customer dao exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public CustomerDaoException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
}
