package com.rabobank.customer.repository;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.rabobank.customer.constant.QueryConstants;
import com.rabobank.customer.constant.ErrorConstants;
import com.rabobank.customer.dto.AddressDTO;
import com.rabobank.customer.dto.CustomerDTO;
import com.rabobank.customer.exception.CustomerDaoException;
import com.rabobank.customer.exception.CustomerNoContentException;

/**
 * The Class CustomerRepositoryImpl.
 */
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	/** The logger. */
	Logger logger = LoggerFactory.getLogger(this.getClass());

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String createCustomer(CustomerDTO customerDto) {

		String result = null;
		AddressDTO addressDTO = customerDto.getCurrentAddres();
		try {
			int existingCustomerCount = jdbcTemplate.queryForObject(QueryConstants.SELECT_CUSTOMER_ID,
					new Object[] { customerDto.getFirstName(), customerDto.getLastName(), customerDto.getAge() },
					Integer.class);
			if (existingCustomerCount == 0) {
				String randomUniqueId = UUID.randomUUID().toString();
				jdbcTemplate.batchUpdate(
						QueryConstants.INSERT_NEW_CUSTOMER + randomUniqueId + "','" + customerDto.getFirstName() + "','"
								+ customerDto.getLastName() + "','" + customerDto.getAge() + "') ",
						QueryConstants.INSERT_NEW_ADDRESS + randomUniqueId + "','" + addressDTO.getLine1() + "','"
								+ addressDTO.getLine2() + "','" + addressDTO.getLine2() + "','" + addressDTO.getCity()
								+ "','" + addressDTO.getCountry() + "','" + addressDTO.getPincode() + "','" + addressDTO.getType() + "', 1 )");
				logger.info(QueryConstants.CUSTOMER_INSERTED_MESSAGE);
				result = QueryConstants.CUSTOMER_INSERTED_MESSAGE + randomUniqueId;
			} else {
				logger.info(QueryConstants.CUSTOMER_ALLREADY_EXIST);
				result = QueryConstants.CUSTOMER_ALLREADY_EXIST;
			}
		} catch (DataAccessException dataEx) {
			logger.error(ErrorConstants.DB_ERROR_CREATE_MESSAGE + customerDto.getFirstName() + " "
					+ customerDto.getLastName(), dataEx.toString());
			throw new CustomerDaoException(ErrorConstants.TECHNICAL_ISSUE_CODE, ErrorConstants.TECHNICAL_ISSUE_MESSAGE);
		} catch (Exception ex) {
			logger.error(ErrorConstants.DB_ERROR_CREATE_MESSAGE + customerDto.getFirstName() + " "
					+ customerDto.getLastName(), ex.toString());
			throw new CustomerDaoException(ErrorConstants.TECHNICAL_GEN_ISSUE_CODE,
					ErrorConstants.TECHNICAL_GEN_ISSUE_MESSAGE);
		}

		return result;
	}


	@Override
	public String updateAddress(AddressDTO addressDTO) {

		String result = null;
		try {
			int updateCounter = jdbcTemplate.update(QueryConstants.UPDATE_ADDRESS, addressDTO.getLine1(),
					addressDTO.getLine2(), addressDTO.getLine3(), addressDTO.getCity(), addressDTO.getCountry(),
					addressDTO.getPincode(),addressDTO.getType(), Integer.parseInt(addressDTO.getVersion())+1, addressDTO.getCustomerId(),Integer.parseInt(addressDTO.getVersion()));
			if (updateCounter == 0) {
				logger.info(QueryConstants.ADDRESS_NOT_EXIST + addressDTO.getCustomerId());
				result = QueryConstants.ADDRESS_NOT_EXIST + addressDTO.getCustomerId();

			} else {
				logger.info(QueryConstants.ADDRESS_UPDATE_MESSAGE + addressDTO.getCustomerId());
				result = QueryConstants.ADDRESS_UPDATE_MESSAGE + addressDTO.getCustomerId();
			}
		} catch (DataAccessException dataEx) {
			logger.error(ErrorConstants.DB_ERROR_UPDATE_MESSAGE + addressDTO.getCustomerId(), dataEx.toString());
			throw new CustomerDaoException(ErrorConstants.TECHNICAL_ISSUE_CODE, ErrorConstants.TECHNICAL_ISSUE_MESSAGE);
		} catch (Exception ex) {
			logger.error(ErrorConstants.DB_ERROR_UPDATE_MESSAGE + addressDTO.getCustomerId(), ex.toString());
			throw new CustomerDaoException(ErrorConstants.TECHNICAL_GEN_ISSUE_CODE,
					ErrorConstants.TECHNICAL_GEN_ISSUE_MESSAGE);
		}

		return result;
	}

	@Override
	public CustomerDTO getCustomerById(String customerId) {

		CustomerDTO customerDTO = null;
		try {
			customerDTO = jdbcTemplate.queryForObject(QueryConstants.SELECT_CUSTOMER_BYID, new Object[] { customerId },
					(rs, rowNum) -> new CustomerDTO(rs.getString(QueryConstants.COLUMN_CUSTOMER_ID),
							rs.getString(QueryConstants.COLUMN_FIRST_NAME),
							rs.getString(QueryConstants.COLUMN_LAST_NAME), rs.getString(QueryConstants.COLUMN_AGE),
							new AddressDTO(rs.getString(QueryConstants.COLUMN_CUSTOMER_ID), rs.getString(QueryConstants.COLUMN_LINE1),
									rs.getString(QueryConstants.COLUMN_LINE2),
									rs.getString(QueryConstants.COLUMN_LINE3), rs.getString(QueryConstants.COLUMN_CITY),
									rs.getString(QueryConstants.COLUMN_COUNTRY),
									rs.getString(QueryConstants.COLUMN_PINCODE),									
									rs.getString(QueryConstants.COLUMN_TYPE),rs.getString(QueryConstants.COLUMN_VERSION))));

		} catch (EmptyResultDataAccessException emptyEx) {
			logger.error(ErrorConstants.DB_CUSTOMER_NOT_EXIST_MESSAGE, customerId);
			throw new CustomerNoContentException(ErrorConstants.DB_CUSTOMER_NOT_EXIST_CODE,
					ErrorConstants.DB_CUSTOMER_NOT_EXIST_MESSAGE + customerId);
		} catch (DataAccessException dataEx) {
			logger.error(ErrorConstants.TECHNICAL_ISSUE_MESSAGE + customerId, dataEx.toString());
			throw new CustomerDaoException(ErrorConstants.TECHNICAL_ISSUE_CODE,
					ErrorConstants.TECHNICAL_ISSUE_MESSAGE + customerId);
		} catch (Exception ex) {
			logger.error(ErrorConstants.TECHNICAL_ISSUE_MESSAGE + customerId, ex.toString());
			throw new CustomerDaoException(ErrorConstants.TECHNICAL_GEN_ISSUE_CODE,
					ErrorConstants.TECHNICAL_GEN_ISSUE_MESSAGE + customerId);
		}

		return customerDTO;
	}

	@Override
	public List<CustomerDTO> getCustomerByName(String firstName, String lastName) {

		List<CustomerDTO> customerDTOList = null;
		String fNameClause = QueryConstants.FISRT_NAME; 
		String lNameClause = QueryConstants.LAST_NAME ;
		String sql = QueryConstants.SEARCH_BY_NAME;
		Object[] objs = null;
		if(firstName != null && lastName != null) {				
			sql = sql + fNameClause + lNameClause;
			objs = new Object[] { firstName.trim(), lastName.trim() };			
		}else {
		if(firstName != null) {			
			sql = sql + fNameClause;
			objs = new Object[] { firstName.trim() };
		}if(lastName != null) {			
			sql = sql + lNameClause;
			objs = new Object[] { lastName.trim() };			
		}}
		try {			
			customerDTOList = jdbcTemplate.query(sql, objs,
					(rs, rowNum) -> new CustomerDTO(rs.getString(QueryConstants.COLUMN_CUSTOMER_ID),
							rs.getString(QueryConstants.COLUMN_FIRST_NAME),
							rs.getString(QueryConstants.COLUMN_LAST_NAME), rs.getString(QueryConstants.COLUMN_AGE),
							new AddressDTO(rs.getString(QueryConstants.COLUMN_CUSTOMER_ID),rs.getString(QueryConstants.COLUMN_LINE1),
									rs.getString(QueryConstants.COLUMN_LINE2),
									rs.getString(QueryConstants.COLUMN_LINE3), rs.getString(QueryConstants.COLUMN_CITY),
									rs.getString(QueryConstants.COLUMN_COUNTRY),
									rs.getString(QueryConstants.COLUMN_PINCODE),
									rs.getString(QueryConstants.COLUMN_TYPE),rs.getString(QueryConstants.COLUMN_VERSION))));
			
		} catch (EmptyResultDataAccessException dataEx) {
			logger.error(ErrorConstants.CUSTOMER_NOT_EXIST_BY_NAME_MESSAGE, dataEx.toString());
			throw new CustomerNoContentException(ErrorConstants.CUSTOMER_NOT_EXIST_BY_NAME_CODE,
					ErrorConstants.CUSTOMER_NOT_EXIST_BY_NAME_MESSAGE + firstName + " " + lastName);
		} catch (DataAccessException dataEx) {
			logger.error(ErrorConstants.TECHNICAL_ISSUE_MESSAGE + firstName + " " + lastName, dataEx.toString());
			throw new CustomerDaoException(ErrorConstants.TECHNICAL_ISSUE_CODE,
					ErrorConstants.TECHNICAL_ISSUE_MESSAGE + firstName + " " + lastName);
		} catch (Exception ex) {
			logger.error(ErrorConstants.TECHNICAL_ISSUE_MESSAGE + firstName + " " + lastName, ex.toString());
			throw new CustomerDaoException(ErrorConstants.TECHNICAL_GEN_ISSUE_CODE,
					ErrorConstants.TECHNICAL_GEN_ISSUE_MESSAGE + firstName + " " + lastName);
		}

		return customerDTOList;
	}

	@Override
	public List<CustomerDTO> findAllCustomer() {
		List<CustomerDTO> customerDTOList = null;
		try {
			customerDTOList = jdbcTemplate.query(QueryConstants.SELECT_ALL_CUSTOMER, (rs, rowNum) -> new CustomerDTO(
					rs.getString(QueryConstants.COLUMN_CUSTOMER_ID), rs.getString(QueryConstants.COLUMN_FIRST_NAME),
					rs.getString(QueryConstants.COLUMN_LAST_NAME), rs.getString(QueryConstants.COLUMN_AGE),
					new AddressDTO(rs.getString(QueryConstants.COLUMN_CUSTOMER_ID), rs.getString(QueryConstants.COLUMN_LINE1), rs.getString(QueryConstants.COLUMN_LINE2),
							rs.getString(QueryConstants.COLUMN_LINE3), rs.getString(QueryConstants.COLUMN_CITY),
							rs.getString(QueryConstants.COLUMN_COUNTRY), rs.getString(QueryConstants.COLUMN_PINCODE),rs.getString(QueryConstants.COLUMN_TYPE),rs.getString(QueryConstants.COLUMN_VERSION))));
		} catch (DataAccessException dataEx) {
			logger.error(ErrorConstants.TECHNICAL_ISSUE_MESSAGE);
			throw new CustomerDaoException(ErrorConstants.TECHNICAL_ISSUE_CODE, ErrorConstants.TECHNICAL_ISSUE_MESSAGE);
		} catch (Exception ex) {
			logger.error(ErrorConstants.TECHNICAL_ISSUE_MESSAGE, ex.toString());
			throw new CustomerDaoException(ErrorConstants.TECHNICAL_GEN_ISSUE_CODE,
					ErrorConstants.TECHNICAL_GEN_ISSUE_MESSAGE);
		}

		return customerDTOList;
	}
}
