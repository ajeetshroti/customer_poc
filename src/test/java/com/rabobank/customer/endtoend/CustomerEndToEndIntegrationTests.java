package com.rabobank.customer.endtoend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.rabobank.customer.constant.MessageConstants;
import com.rabobank.customer.dto.AddressDTO;
import com.rabobank.customer.dto.CustomerDTO;

public class CustomerEndToEndIntegrationTests extends AbstractCustomerTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void createCustomerSuccess() throws Exception {
		String uri = MessageConstants.CREATE_CUSTOMER_REQUEST_MAPPING;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Calendar cal = Calendar.getInstance();
		String customerNewAge = sdf.format(cal.getTime());
		CustomerDTO customerDTO = new CustomerDTO(null, "abc", "xyz", customerNewAge,
				new AddressDTO(null,"line1", "line2", "line3", "city", "country", "pincode", "C" ,"1"));
		String inputJson = super.mapToJson(customerDTO);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(200, status);
		assertTrue(content.contains("Customer sucessfully created with cutomer Id"));
	}

	@Test
	public void createCustomerAllreadyExist() throws Exception {
		String uri = MessageConstants.CREATE_CUSTOMER_REQUEST_MAPPING;
		CustomerDTO customerDTO = new CustomerDTO(null, "Mark", "Jon", "25",
				new AddressDTO(null,"line1", "line2", "line3", "city", "country", "pincode", "C" ,"1"));
		String inputJson = super.mapToJson(customerDTO);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(200, status);
		assertEquals(content, "Customer allready exist");
	}

	@Test
	public void updateAddressFailed() throws Exception {
		String uri = MessageConstants.UPDATE_ADDRESS_REQUEST_MAPPING;
		String customerID = "blabla";
		AddressDTO addressDTO = new AddressDTO(customerID,"line1", "line2", "line3", "city", "country", "pincode","C" ,"1");
		String inputJson = super.mapToJson(addressDTO);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(405, status);
	}

	@Test
	public void getCustomerById() throws Exception {
		String uri = "/getcustomerbyid/9009009001";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		CustomerDTO customerDto = super.mapFromJson(content, CustomerDTO.class);
		assertEquals("9009009001", customerDto.getCustomerId());
	}

	@Test
	public void getAllCustomerByName() throws Exception {
		String uri = MessageConstants.SEARCH_CUSTOMER_BY_NAME_REQUEST_MAPPING + "?firstname=Mark&lastname=Jon";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		CustomerDTO[] customerlist = super.mapFromJson(content, CustomerDTO[].class);
		assertTrue(customerlist.length > 0);
	}

	@Test
	public void getAllCustomerList() throws Exception {
		String uri = MessageConstants.SEARCH_ALL_CUSTOMER_REQUEST_MAPPING;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		CustomerDTO[] customerlist = super.mapFromJson(content, CustomerDTO[].class);
		assertTrue(customerlist.length > 0);
	}
}
