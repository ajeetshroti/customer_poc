package com.rabobank.customer.dto;

/**
 * The Class AddressDTO holds address related fields.
 */
public class AddressDTO {

	/** The customer id. */
	private String customerId;
	
	/** The line 1. */
	private String line1;
	
	/** The line 2. */
	private String line2;
	
	/** The line 3. */
	private String line3;
	
	/** The city. */
	private String city;
	
	/** The country. */
	private String country;
	
	/** The pincode. */
	private String pincode;
	
	/** The type. */
	private String type;
	
	/** The version. */
	private String version;

	/**
	 * Instantiates a new address DTO.
	 */
	public AddressDTO() {
	};

	/**
	 * Instantiates a new address DTO.
	 *
	 * @param customerId the customer id
	 * @param line1 the line 1
	 * @param line2 the line 2
	 * @param line3 the line 3
	 * @param city the city
	 * @param country the country
	 * @param pincode the pincode
	 * @param type the type
	 * @param version the version
	 */
	public AddressDTO(String customerId, String line1, String line2, String line3, String city, String country,
			String pincode, String type, String version) {
		super();
		this.customerId = customerId;
		this.line1 = line1;
		this.line2 = line2;
		this.line3 = line3;
		this.city = city;
		this.country = country;
		this.pincode = pincode;
		this.type = type;
		this.version = version;
	}

	/**
	 * Gets the customer id.
	 *
	 * @return the customer id
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the customer id.
	 *
	 * @param customerId the new customer id
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * Gets the line 1.
	 *
	 * @return the line 1
	 */
	public String getLine1() {
		return line1;
	}

	/**
	 * Sets the line 1.
	 *
	 * @param line1 the new line 1
	 */
	public void setLine1(String line1) {
		this.line1 = line1;
	}

	/**
	 * Gets the line 2.
	 *
	 * @return the line 2
	 */
	public String getLine2() {
		return line2;
	}

	/**
	 * Sets the line 2.
	 *
	 * @param line2 the new line 2
	 */
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	/**
	 * Gets the line 3.
	 *
	 * @return the line 3
	 */
	public String getLine3() {
		return line3;
	}

	/**
	 * Sets the line 3.
	 *
	 * @param line3 the new line 3
	 */
	public void setLine3(String line3) {
		this.line3 = line3;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the pincode.
	 *
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * Sets the pincode.
	 *
	 * @param pincode the new pincode
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "AddressDTO [customerId=" + customerId + ", line1=" + line1 + ", line2=" + line2 + ", line3=" + line3
				+ ", city=" + city + ", country=" + country + ", pincode=" + pincode + ", type=" + type + ", version="
				+ version + "]";
	}

}
