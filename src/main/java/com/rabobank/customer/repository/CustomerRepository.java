package com.rabobank.customer.repository;

import java.util.List;

import com.rabobank.customer.dto.AddressDTO;
import com.rabobank.customer.dto.CustomerDTO;

/**
 * The Interface CustomerRepository used for interacting with database using spring JDBC.
 */
public interface CustomerRepository {
	
	/**
	 * Creates the customer.
	 *
	 * @param custDTO the customer object
	 * @return the string
	 */
	public String createCustomer(CustomerDTO custDTO);

	/**
	 * Update address.
	 *
	 * @param addressDTO the address object
	 * @return the string
	 */
	public String updateAddress(AddressDTO addressDTO);

	/**
	 * Gets the customer by id.
	 *
	 * @param customerId the customer id
	 * @return the customer object
	 */
	public CustomerDTO getCustomerById(String customerId);
	
	/**
	 * Gets the customer by first name and /or last name.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @return the list of customers
	 */
	public List<CustomerDTO> getCustomerByName(String firstName, String lastName);

	/**
	 * Find all customer.
	 *
	 * @return the list of customers
	 */
	public List<CustomerDTO> findAllCustomer();
}
