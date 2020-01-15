package com.rabobank.customer.validation;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.rabobank.customer.dto.AddressDTO;
import com.rabobank.customer.dto.CustomerDTO;
import com.rabobank.customer.endtoend.AbstractCustomerTest;
import com.rabobank.customer.exception.CustomerValidationException;

public class CustomerValidationTest extends AbstractCustomerTest {

	@Autowired
	CustomerValidation customerValidation;

	@Test
	public void validateCustomerObjForCreate() throws Exception {

		CustomerDTO customerDTO = new CustomerDTO(null, "abc", "xyz", "32",
				new AddressDTO(null,"line1", "line2", "line3", "city", "country", "pincode","C","1"));
		customerValidation.validateCreateCustomer(customerDTO);
	}

	@Test(expected = CustomerValidationException.class)
	public void validateCustomerFailedWithoutFisrt() throws Exception {
		String customerId = "123456"; 
		CustomerDTO customerDTO = new CustomerDTO(customerId, null, "xyz", "32",
				new AddressDTO(null,"line1", "line2", "line3", "city", "country", "pincode","C","1"));
		customerValidation.validateCreateCustomer(customerDTO);
	}

	@Test(expected = CustomerValidationException.class)
	public void validateCustomerFailedWithoutLast() throws Exception {
		String customerId = "123456";
		CustomerDTO customerDTO = new CustomerDTO(customerId, "abc", null, "32",
				new AddressDTO(null,"line1", "line2", "line3", "city", "country", "pincode","C","1"));
		customerValidation.validateCreateCustomer(customerDTO);
	}

	@Test(expected = CustomerValidationException.class)
	public void validateCustomerFailedWithoutAge() throws Exception {

		CustomerDTO customerDTO = new CustomerDTO(null, "abc", "xyz", null,
				new AddressDTO(null,"line1", "line2", "line3", "city", "country", "pincode","C","1"));
		customerValidation.validateCreateCustomer(customerDTO);
	}

	@Test
	public void validatevalidateUpdateAddressSuccess() throws Exception {
        String customerId = "123456";
		AddressDTO AddressDTO = new AddressDTO(customerId,"line1", "line2", "line3", "city", "country", "pincode","C","1");
		customerValidation.validateUpdateAddress(AddressDTO);
	}

	@Test(expected = CustomerValidationException.class)
	public void validatevalidateUpdateAddressFaild() throws Exception {

		AddressDTO AddressDTO = new AddressDTO(null,"line1", "line2", "line3", "city", "country", "pincode","C","1");
		customerValidation.validateUpdateAddress(AddressDTO);
	}

	@Test
	public void validateGetCustomerByIdSucess() {

		String customerId = "123455778";
		customerValidation.validateGetCustomerById(customerId);

	}

	@Test(expected = CustomerValidationException.class)
	public void validateGetCustomerByIdFailed() {

		String customerId = "";
		customerValidation.validateGetCustomerById(customerId);

	}

	@Test
	public void validateGetCustomerByName() {
		String firstName = "Mark";
		String lastName = "JON";
		customerValidation.validateGetCustomerByName(firstName, lastName);
	}

	@Test(expected = CustomerValidationException.class)
	public void validateGetCustomerByNameFailed() {
		String firstName = null;
		String lastName = null;
		customerValidation.validateGetCustomerByName(firstName, lastName);
	}
}
