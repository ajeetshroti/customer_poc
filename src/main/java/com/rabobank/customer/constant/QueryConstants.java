package com.rabobank.customer.constant;

/**
 * The Class QueryConstants.
 */
public final class QueryConstants {

	/** The Constant SELECT_CUSTOMER_ID. */
	public static final String SELECT_CUSTOMER_ID = "select count(*) from  customers where first_name = ? and last_name=? and age=?";
	
	/** The Constant INSERT_NEW_CUSTOMER. */
	public static final String INSERT_NEW_CUSTOMER = "insert into customers (customer_id, first_name, last_name, age ) values ('";
	
	/** The Constant INSERT_NEW_ADDRESS. */
	public static final String INSERT_NEW_ADDRESS = "insert into address (customer_id, line1, line2, line3, city, country, pincode, type, version ) values('";
	
	/** The Constant UPDATE_ADDRESS. */
	public static final String UPDATE_ADDRESS = "update  address set line1 = ?, line2 = ?, line3 = ?, city = ?, country = ?, pincode = ?,type = ?, version= ?  where customer_id = ? and version = ?";
	
	/** The Constant SELECT_CUSTOMER_BYID. */
	public static final String SELECT_CUSTOMER_BYID = "select customers.customer_id, first_name, last_name, age, line1, line2, line3, city, country, pincode, type, version from customers, address where  customers.customer_id=address.customer_id and customers.customer_id = ?";
	
	/** The Constant SELECT_ALL_CUSTOMER. */
	public static final String SELECT_ALL_CUSTOMER = "select customers.customer_id, first_name, last_name, age, line1, line2, line3, city, country, pincode, type, version  from customers, address where customers.customer_id=address.customer_id";
	
	/** The Constant CUSTOMER_INSERTED_MESSAGE. */
	public static final String CUSTOMER_INSERTED_MESSAGE = "Customer sucessfully created with cutomer Id : ";
	
	/** The Constant CUSTOMER_ALLREADY_EXIST. */
	public static final String CUSTOMER_ALLREADY_EXIST = "Customer allready exist";
	
	/** The Constant ADDRESS_UPDATE_MESSAGE. */
	public static final String ADDRESS_UPDATE_MESSAGE = "Address has been updated successfully for CustomerId : ";
	
	/** The Constant ADDRESS_NOT_EXIST. */
	public static final String ADDRESS_NOT_EXIST = "Customer does not exist or existng address has been updated for customerId : ";
	
	/** The Constant FISRT_NAME. */
	public static final String FISRT_NAME = " and first_name = ?";
	
	/** The Constant LAST_NAME. */
	public static final String LAST_NAME = " and last_name= ?";
	
	/** The Constant SEARCH_BY_NAME. */
	public static final String SEARCH_BY_NAME = "select customers.customer_id, first_name, last_name, age, line1, line2, line3, city, country, pincode, type, version from customers, address where  customers.customer_id=address.customer_id";
	
	/** The Constant COLUMN_CUSTOMER_ID. */
	public static final String COLUMN_CUSTOMER_ID = "customer_id";
	
	/** The Constant COLUMN_FIRST_NAME. */
	public static final String COLUMN_FIRST_NAME = "first_name";
	
	/** The Constant COLUMN_LAST_NAME. */
	public static final String COLUMN_LAST_NAME = "last_name";
	
	/** The Constant COLUMN_AGE. */
	public static final String COLUMN_AGE = "age";
	
	/** The Constant COLUMN_LINE1. */
	public static final String COLUMN_LINE1 = "line1";
	
	/** The Constant COLUMN_LINE2. */
	public static final String COLUMN_LINE2 = "line2";
	
	/** The Constant COLUMN_LINE3. */
	public static final String COLUMN_LINE3 = "line3";
	
	/** The Constant COLUMN_CITY. */
	public static final String COLUMN_CITY = "city";
	
	/** The Constant COLUMN_COUNTRY. */
	public static final String COLUMN_COUNTRY = "country";
	
	/** The Constant COLUMN_PINCODE. */
	public static final String COLUMN_PINCODE = "pincode";
	
	/** The Constant COLUMN_TYPE. */
	public static final String COLUMN_TYPE = "type";
	
	/** The Constant COLUMN_VERSION. */
	public static final String COLUMN_VERSION = "version";
}
