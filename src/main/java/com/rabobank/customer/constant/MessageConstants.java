package com.rabobank.customer.constant;

/**
 * The Class MessageConstants.
 */
public final class MessageConstants {

	/** The Constant CUSTOMER_URL. */
	public static final String CUSTOMER_URL = "/customers";
	
	/** The Constant CREATE_CUSTOMER_REQUEST_MAPPING. */
	public static final String CREATE_CUSTOMER_REQUEST_MAPPING = "/addcustomer";
	
	/** The Constant CREATE_CUSTOMER_REQUEST. */
	public static final String CREATE_CUSTOMER_REQUEST = "Creation request for customer {}";
	
	/** The Constant UPDATE_ADDRESS_REQUEST_MAPPING. */
	public static final String UPDATE_ADDRESS_REQUEST_MAPPING = "/updateaddress";
	
	/** The Constant UPDATE_ADDRESS_REQUEST. */
	public static final String UPDATE_ADDRESS_REQUEST = "Address updation  request for customer address {}";
	
	/** The Constant SEARCH_CUSTOMER_REQUEST_MAPPING. */
	public static final String SEARCH_CUSTOMER_REQUEST_MAPPING = "/getcustomerbyid/{customerId}";
	
	/** The Constant SEARCH_CUSTOMER_REQUEST. */
	public static final String SEARCH_CUSTOMER_REQUEST = "Customer search request for customerId : ";
	
	/** The Constant SEARCH_ALL_CUSTOMER_REQUEST_MAPPING. */
	public static final String SEARCH_ALL_CUSTOMER_REQUEST_MAPPING = "/allcustomer";
	
	/** The Constant SEARCH_ALL_CUSTOMER_REQUEST. */
	public static final String SEARCH_ALL_CUSTOMER_REQUEST = "All customer search request ";
	
	/** The Constant SEARCH_CUSTOMER_BY_NAME_REQUEST_MAPPING. */
	public static final String SEARCH_CUSTOMER_BY_NAME_REQUEST_MAPPING = "/getcustomerbyname";
	
	/** The Constant SEARCH_CUSTOMER_BY_NAME_REQUEST. */
	public static final String SEARCH_CUSTOMER_BY_NAME_REQUEST = "Customer search request with custome name : ";
}
