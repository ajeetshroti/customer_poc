package com.rabobank.customer.validation;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.rabobank.customer.constant.ErrorConstants;
import com.rabobank.customer.dto.AddressDTO;
import com.rabobank.customer.dto.CustomerDTO;
import com.rabobank.customer.exception.CustomerValidationException;

/**
 * The Class CustomerValidation used for validating the received request data.
 */
@Component
public class CustomerValidation {

	/** The logger. */
	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Validate request data for customer creation
	 *
	 * @param custDTO the customer Object.
	 */
	public void validateCreateCustomer(CustomerDTO custDTO) {

		if (Objects.isNull(custDTO.getFirstName()) || custDTO.getFirstName().equals("")) {
			logger.error(ErrorConstants.FIRST_NAME_MESSAGE);
			throw new CustomerValidationException(ErrorConstants.FIRST_NAME_CODE, ErrorConstants.FIRST_NAME_MESSAGE);
		}

		if (Objects.isNull(custDTO.getLastName()) || custDTO.getLastName().equals("")) {
			logger.error(ErrorConstants.LAST_NAME_MESSAGE);
			throw new CustomerValidationException(ErrorConstants.LAST_NAME_CODE, ErrorConstants.LAST_NAME_MESSAGE);
		}

		if (Objects.isNull(custDTO.getAge()) || custDTO.getAge().equals("")) {
			logger.error(ErrorConstants.AGE_MESSAGE);
			throw new CustomerValidationException(ErrorConstants.AGE_CODE, ErrorConstants.AGE_MESSAGE);
		}

		if (Objects.isNull(custDTO.getCurrentAddres()) || Objects.isNull(custDTO.getCurrentAddres().getCountry())
				|| custDTO.getCurrentAddres().getCountry().equals("")) {
			logger.error(ErrorConstants.COUNTRY_MESSAGE);
			throw new CustomerValidationException(ErrorConstants.COUNTRY_CODE, ErrorConstants.COUNTRY_MESSAGE);
		}

	}

	/**
	 * Validate request data for address update.
	 *
	 * @param addressDTO the address object.
	 */
	public void validateUpdateAddress(AddressDTO addressDTO) {

		if (Objects.isNull(addressDTO.getCustomerId()) || addressDTO.getCustomerId().equals("")) {
			logger.error(ErrorConstants.CUSTOMER_ID_MESSAGE);
			throw new CustomerValidationException(ErrorConstants.CUSTOMER_ID_CODE, ErrorConstants.CUSTOMER_ID_MESSAGE);
		}

		if (Objects.isNull(addressDTO.getLine1()) || addressDTO.getLine1().equals("")) {
			logger.error(ErrorConstants.LINE1_MESSAGE);
			throw new CustomerValidationException(ErrorConstants.LINE1_CODE, ErrorConstants.LINE1_MESSAGE);
		}

		if (Objects.isNull(addressDTO.getCity()) || addressDTO.getCity().equals("")) {
			logger.error(ErrorConstants.CITY_MESSAGE);
			throw new CustomerValidationException(ErrorConstants.CITY_CODE, ErrorConstants.CITY_MESSAGE);
		}

		if (Objects.isNull(addressDTO.getCountry()) || addressDTO.getCountry().equals("")) {
			logger.error(ErrorConstants.COUNTRY_MESSAGE);
			throw new CustomerValidationException(ErrorConstants.COUNTRY_CODE, ErrorConstants.COUNTRY_MESSAGE);
		}

	}

	/**
	 * Validate request data for search customer by customer id.
	 *
	 * @param customerId the customer id
	 * @return the string
	 */
	public String validateGetCustomerById(String customerId) {

		if (Objects.isNull(customerId) || customerId.equals("")) {
			throw new CustomerValidationException(ErrorConstants.CUSTOMER_ID_CODE, ErrorConstants.CUSTOMER_ID_MESSAGE);
		}		
		return customerId.trim();

	}

	/**
	 * Validate request data for search customer by first and /or last name.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public void validateGetCustomerByName(String firstName, String lastName) {
		if (Objects.isNull(firstName) && Objects.isNull(lastName)) {
			throw new CustomerValidationException(ErrorConstants.NAME_CODE, ErrorConstants.NAME_MESSAGE);
		}	
	}
}
