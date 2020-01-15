package com.rabobank.customer.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rabobank.customer.dao.CustomerDao;
import com.rabobank.customer.dto.AddressDTO;
import com.rabobank.customer.dto.CustomerDTO;
import com.rabobank.customer.validation.CustomerValidation;

/**
 * The Class CustomerServiceImpl implements business logic at service layer.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerDao custDao;

	@Autowired
	CustomerValidation customerValidation;

	@Override
	public String createCustomer(CustomerDTO custDTO) {

		customerValidation.validateCreateCustomer(custDTO);
		return custDao.createCustomer(custDTO);
	}

	@Override
	public String updateAddress(AddressDTO addressDTO) {

		customerValidation.validateUpdateAddress(addressDTO);
		return custDao.updateAddress(addressDTO);
	}

	@Override
	public CustomerDTO getCustomerById(String customerId) {

		String customerIdParam = customerValidation.validateGetCustomerById(customerId);
		return custDao.getCustomerById(customerIdParam);
	}

	@Override
	public List<CustomerDTO> getCustomerByName(String firstName, String lastName) {

		customerValidation.validateGetCustomerByName(firstName, lastName);
		return custDao.getCustomerByName(firstName, lastName);
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {

		return custDao.getAllCustomers();
	}
}
