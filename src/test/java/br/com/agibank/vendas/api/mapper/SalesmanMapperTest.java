package br.com.agibank.vendas.api.mapper;

import java.math.BigDecimal;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.agibank.vendas.api.model.Salesman;

public class SalesmanMapperTest {

	@Test
	public void returnNullWhenEmptyString() {
		String line = "";
		Scanner scan = new Scanner(line);

		Salesman salesman = SalesmanMapper.scanToSalesman(scan);

		Assertions.assertNull(salesman);
	}

	@Test
	public void returnNullWhenNull() {
		Salesman salesman = SalesmanMapper.scanToSalesman(null);

		Assertions.assertNull(salesman);
	}

	@Test
	public void returnCustomerWhenValidLine() {
		String line = "73412508012çJim Morrisonç140.00";
		Scanner scan = new Scanner(line).useDelimiter("ç");

		Salesman salesman = SalesmanMapper.scanToSalesman(scan);

		Assertions.assertNotNull(salesman);
		Assertions.assertEquals(salesman.getCpf(), "73412508012");
		Assertions.assertEquals(salesman.getName(), "Jim Morrison");
		Assertions.assertEquals(salesman.getSalary(), new BigDecimal("140.00"));
	}

}
