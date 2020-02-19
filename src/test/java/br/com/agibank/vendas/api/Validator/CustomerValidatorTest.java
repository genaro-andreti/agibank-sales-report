package br.com.agibank.vendas.api.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.agibank.vendas.api.validator.CustomerValidator;

public class CustomerValidatorTest {

	@Test
	public void returnTrueWhenValidCustomer() {
		String cnpj = "75998128000195";
		String name = "William Wallace";
		String businessArea = "HUMAN RESOURCE";
		Boolean isValidCustomer = CustomerValidator.validateCustomer(cnpj, name, businessArea);
		Assertions.assertTrue(isValidCustomer);
	}

	@Test
	public void returnFalseWhenNoCustomerName() {
		String cnpj = "75998128000195";
		String name = null;
		String businessArea = "HUMAN RESOURCE";
		Boolean isValidCustomer = CustomerValidator.validateCustomer(cnpj, name, businessArea);
		Assertions.assertFalse(isValidCustomer);

	}

	@Test
	public void returnFalseWhenNoCustomerBusinessArea() {
		String cnpj = "75998128000195";
		String name = "William Wallace";
		String businessArea = null;
		Boolean isValidCustomer = CustomerValidator.validateCustomer(cnpj, name, businessArea);
		Assertions.assertFalse(isValidCustomer);

	}

	@Test
	public void returnFalseWhenNoCustomerBusinessAreaAndName() {
		String cnpj = "75998128000195";
		String name = null;
		String businessArea = null;
		Boolean isValidCustomer = CustomerValidator.validateCustomer(cnpj, name, businessArea);
		Assertions.assertFalse(isValidCustomer);

	}

	@Test
	public void returnFalseWhenSmallerCnpj() {
		String cnpj = "7599812800019";
		String name = "William Wallace";
		String businessArea = "HUMAN RESOURCE";
		Boolean isValidCustomer = CustomerValidator.validateCustomer(cnpj, name, businessArea);
		Assertions.assertFalse(isValidCustomer);

	}

	@Test
	public void returnFalseWhenBiggerCnpj() {
		String cnpj = "759981280001950";
		String name = "William Wallace";
		String businessArea = "HUMAN RESOURCE";
		Boolean isValidCustomer = CustomerValidator.validateCustomer(cnpj, name, businessArea);
		Assertions.assertFalse(isValidCustomer);
	}

	@Test
	public void returnFalseWhenCnpjWithLetter() {
		String cnpj = "7599812800019aa";
		String name = "William Wallace";
		String businessArea = "HUMAN RESOURCE";
		Boolean isValidCustomer = CustomerValidator.validateCustomer(cnpj, name, businessArea);
		Assertions.assertFalse(isValidCustomer);
	}

}
