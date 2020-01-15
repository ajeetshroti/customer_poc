package com.rabobank.customer.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rabobank.customer.constant.MessageConstants;
import com.rabobank.customer.dto.AddressDTO;
import com.rabobank.customer.dto.CustomerDTO;
import com.rabobank.customer.service.CustomerService;

/**
 * The Class CustomerController used for serving REST API for customer management.
 * 
 * @author  Ajeet Shroti
 */
@RestController
public class CustomerController {

	/** The logger. */
	Logger logger = LoggerFactory.getLogger(this.getClass());

	/** The cust service. */
	@Autowired
	CustomerService custService;

	/**
	 * This method is used for serving customer create requests.
	 *
	 * @param custDTO the customer data
	 * @return ResponseEntity with response
	 */
	@RequestMapping(value = MessageConstants.CREATE_CUSTOMER_REQUEST_MAPPING, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createCustomer(@RequestBody CustomerDTO custDTO) {
		logger.info(MessageConstants.CREATE_CUSTOMER_REQUEST, custDTO);
		String result = custService.createCustomer(custDTO);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * This method is used for serving customer address update requests.
	 *
	 * @param addressDTO the address data
	 * @return ResponseEntity with response
	 */
	@RequestMapping(value = MessageConstants.UPDATE_ADDRESS_REQUEST_MAPPING, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAddress(@RequestBody AddressDTO addressDTO) {
		logger.info(MessageConstants.UPDATE_ADDRESS_REQUEST, addressDTO);
		String result = custService.updateAddress(addressDTO);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * This method is used for serving search customer by id.
	 *
	 * @param CustomerId the customer id
	 * @return ResponseEntity with customer object
	 */
	@RequestMapping(value = MessageConstants.SEARCH_CUSTOMER_REQUEST_MAPPING, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCustomerById(@PathVariable String customerId) {

		logger.info(MessageConstants.SEARCH_CUSTOMER_REQUEST, customerId);
		CustomerDTO customerDTO = custService.getCustomerById(customerId);
		return new ResponseEntity<Object>(customerDTO, HttpStatus.OK);
	}

	/**
	 * This method is used for serving search customer by first name and/or last name.
	 *
	 * @param firstname the customer first name
	 * @param lastname the customer last name
	 * @return ResponseEntity with list of customer
	 */
	@RequestMapping(value = MessageConstants.SEARCH_CUSTOMER_BY_NAME_REQUEST_MAPPING, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerDTO>> getCustomerByName(@RequestParam(name="firstname", required = false) String firstname,
			@RequestParam(name="lastname", required = false) String lastname) {

		logger.info(MessageConstants.SEARCH_CUSTOMER_BY_NAME_REQUEST, firstname + " " + lastname);
		List<CustomerDTO> customerDtoList = custService.getCustomerByName(firstname, lastname);
		return new ResponseEntity<List<CustomerDTO>>(customerDtoList, HttpStatus.OK);
	}

	/**
	 * This method is used for serving search all customer.
	 *
	 * @return ResponseEntity with list of customer
	 */
	@RequestMapping(value = MessageConstants.SEARCH_ALL_CUSTOMER_REQUEST_MAPPING, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerDTO>> findAllCustomers() {

		logger.info(MessageConstants.SEARCH_ALL_CUSTOMER_REQUEST);
		List<CustomerDTO> customerDtoList = custService.getAllCustomers();
		return new ResponseEntity<List<CustomerDTO>>(customerDtoList, HttpStatus.OK);
	}
}
