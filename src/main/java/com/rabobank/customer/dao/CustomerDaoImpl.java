package com.rabobank.customer.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rabobank.customer.dto.AddressDTO;
import com.rabobank.customer.dto.CustomerDTO;
import com.rabobank.customer.repository.CustomerRepository;

/**
 * The Class CustomerDaoImpl is used for invoke repository layer.
 */
@Component
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public String createCustomer(CustomerDTO custDTO) {

		return customerRepo.createCustomer(custDTO);
	}

	@Override
	public String updateAddress(AddressDTO addressDTO) {

		return customerRepo.updateAddress(addressDTO);
	}

	@Override
	public CustomerDTO getCustomerById(String customerId) {

		return customerRepo.getCustomerById(customerId);
	}

	@Override
	public List<CustomerDTO> getCustomerByName(String firstName, String lastName) {

		return customerRepo.getCustomerByName(firstName, lastName);
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {

		return customerRepo.findAllCustomer();
	}
}
