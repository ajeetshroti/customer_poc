package com.rabobank.customer.exception;

/**
 * The Class CustomerNoContentException.
 */
public class CustomerNoContentException extends CustomerException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The code. */
	public String code;
	
	/** The message. */
	public String message;

	/**
	 * Instantiates a new customer no content exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public CustomerNoContentException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
}
