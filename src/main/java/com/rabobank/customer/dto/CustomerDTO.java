package com.rabobank.customer.dto;

/**
 * The Class CustomerDTO holds address related fields.
 */
public class CustomerDTO {

	/**
	 * Instantiates a new customer DTO.
	 */
	public CustomerDTO() {
	}

	/**
	 * Instantiates a new customer DTO.
	 *
	 * @param customerId the customer id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param age the age
	 * @param currentAddres the current addres
	 */
	public CustomerDTO(String customerId, String firstName, String lastName, String age, AddressDTO currentAddres) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.currentAddres = currentAddres;
	}

	/** The customer id. */
	private String customerId;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The age. */
	private String age;

	/** The current addres. */
	private AddressDTO currentAddres;

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
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * Gets the current addres.
	 *
	 * @return the current addres
	 */
	public AddressDTO getCurrentAddres() {
		return currentAddres;
	}

	/**
	 * Sets the current addres.
	 *
	 * @param currentAddres the new current addres
	 */
	public void setCurrentAddres(AddressDTO currentAddres) {
		this.currentAddres = currentAddres;
	}


	@Override
	public String toString() {
		return "CustomerDTO [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", currentPlan="
				+ currentAddres + "]";
	}
}
