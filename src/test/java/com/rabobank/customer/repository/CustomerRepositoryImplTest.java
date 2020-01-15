package com.rabobank.customer.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabobank.customer.constant.QueryConstants;
import com.rabobank.customer.dto.AddressDTO;
import com.rabobank.customer.dto.CustomerDTO;
import com.rabobank.customer.endtoend.AbstractCustomerTest;
import com.rabobank.customer.exception.CustomerNoContentException;

public class CustomerRepositoryImplTest extends AbstractCustomerTest {

	@Autowired
	private CustomerRepository customerRepo;

	@Test
	public void createCustomerButExist() throws Exception {

		CustomerDTO customerDTO = new CustomerDTO(null, "Ram", "Singh", "52",
				new AddressDTO(null, "line1", "line2", "line3", "city", "country", "pincode", "C", "1"));
		String result = customerRepo.createCustomer(customerDTO);
		assertEquals(result, "Customer allready exist");
	}

	@Test
	public void createCustomerSuccess() throws Exception {

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Calendar cal = Calendar.getInstance();
		String customerNewAge = sdf.format(cal.getTime());

		CustomerDTO customerDTO = new CustomerDTO(null, "abc", "xyz", customerNewAge,
				new AddressDTO(null,"line1", "line2", "line3", "city", "country", "pincode", "C", "1"));
		String result = customerRepo.createCustomer(customerDTO);
		assertTrue(result.contains("Customer sucessfully created with cutomer Id"));
	}

	@Test
	public void updateAddressFailed() throws Exception {

		String customerID = "blabla";
		AddressDTO addressDTO = new AddressDTO(customerID,"line1", "line2", "line3", "city", "country", "pincode","C", "1");
		String result = customerRepo.updateAddress(addressDTO);
		assertEquals(result, QueryConstants.ADDRESS_NOT_EXIST + customerID);
	}

	@Test
	public void getCustomerById() throws Exception {
		String customerId = "9009009001";
		CustomerDTO customerDto = customerRepo.getCustomerById(customerId);
		assertEquals("9009009001", customerDto.getCustomerId());
	}

	@Test(expected = CustomerNoContentException.class)
	public void getCustomerByIdNoData1() throws Exception {
		String customerId = "blabla";
		CustomerDTO customerDto = customerRepo.getCustomerById(customerId);
		assertTrue(customerDto == null);
	}

	@Test(expected = CustomerNoContentException.class)
	public void getCustomerByIdNoData2() throws Exception {
		String customerId = null;
		CustomerDTO customerDto = customerRepo.getCustomerById(customerId);
		assertTrue(customerDto == null);
	}

	@Test
	public void getAllCustomerByName() throws Exception {

		String firstname = "Mark";
		String lastname = "Jon";
		List<CustomerDTO> customerlist = customerRepo.getCustomerByName(firstname, lastname);
		assertTrue(customerlist.size() > 0);
	}

	@Test
	public void getAllCustomerByNameNoData1() throws Exception {

		String firstname = "";
		String lastname = "Jon";
		List<CustomerDTO> customerlist = customerRepo.getCustomerByName(firstname, lastname);
		assertTrue(customerlist.isEmpty());
	}

	@Test
	public void getAllCustomerByNameNoData2() throws Exception {

		String firstname = "Mark";
		String lastname = "";
		List<CustomerDTO> customerlist = customerRepo.getCustomerByName(firstname, lastname);
		assertTrue(customerlist.isEmpty());
	}

	@Test
	public void getAllCustomerByNameNoData3() throws Exception {

		String firstname = null;
		String lastname = "";
		List<CustomerDTO> customerlist = customerRepo.getCustomerByName(firstname, lastname);
		assertTrue(customerlist.isEmpty());
	}

	@Test
	public void getAllCustomerList() throws Exception {

		List<CustomerDTO> customerlist = customerRepo.findAllCustomer();
		assertTrue(customerlist.size() > 0);
	}
}
