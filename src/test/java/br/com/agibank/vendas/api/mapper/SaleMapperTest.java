package br.com.agibank.vendas.api.mapper;

import java.math.BigDecimal;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.agibank.vendas.api.model.Sale;

public class SaleMapperTest {

	@Test
	public void shouldReturnNullWhenReceiveEmptyString() {
		String line = "";
		Scanner scan = new Scanner(line);

		Sale sale = SaleMapper.scanToSale(scan);

		Assertions.assertNull(sale);
	}

	@Test
	public void shouldReturnNullWhenReceiveNull() {
		Sale sale = SaleMapper.scanToSale(null);

		Assertions.assertNull(sale);
	}

	@Test
	public void shouldReturnCustomerWhenReceiveValidLine() {
		String line = "13ç[13-2-10, 5-1-10]çJim Morrison";
		Scanner scan = new Scanner(line).useDelimiter("ç");

		Sale sale = SaleMapper.scanToSale(scan);

		Assertions.assertNotNull(sale);
		Assertions.assertEquals(sale.getSaleId(), "13");
		Assertions.assertEquals(sale.getSalesmanName(), "Jim Morrison");
		Assertions.assertFalse(sale.getItemList().isEmpty());
		Assertions.assertEquals(sale.getItemList().size(), 2);
		Assertions.assertEquals(sale.getItemList().get(0).getId(), "13");
		Assertions.assertEquals(sale.getItemList().get(0).getQuantity(), 2);
		Assertions.assertEquals(sale.getItemList().get(0).getPrice(), new BigDecimal("10"));
		Assertions.assertEquals(sale.getItemList().get(1).getId(), "5");
		Assertions.assertEquals(sale.getItemList().get(1).getQuantity(), 1);
		Assertions.assertEquals(sale.getItemList().get(1).getPrice(), new BigDecimal("10"));
	}

}
