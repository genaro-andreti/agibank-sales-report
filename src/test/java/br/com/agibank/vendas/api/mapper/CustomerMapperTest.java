package br.com.agibank.vendas.api.mapper;

import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.agibank.vendas.api.model.Customer;

public class CustomerMapperTest {

	@Test
	public void returnNullWhenEmptyString() {
		String line = "";
		Scanner scan = new Scanner(line);

		Customer customer = CustomerMapper.scanToCustomer(scan);

		Assertions.assertNull(customer);
	}

	@Test
	public void returnNullWhenNull() {
		Customer customer = CustomerMapper.scanToCustomer(null);

		Assertions.assertNull(customer);
	}

	@Test
	public void returnCustomerWhenValidLine() {
		String line = "75998128000195çWilliam WallaceçHUMAN RESOURCE";
		Scanner scan = new Scanner(line).useDelimiter("ç");

		Customer customer = CustomerMapper.scanToCustomer(scan);

		Assertions.assertNotNull(customer);
		Assertions.assertEquals(customer.getCnpj(), "75998128000195");
		Assertions.assertEquals(customer.getName(), "William Wallace");
		Assertions.assertEquals(customer.getBusinessArea(), "HUMAN RESOURCE");
	}

}
