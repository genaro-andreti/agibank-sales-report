package br.com.agibank.vendas.api.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.agibank.vendas.api.model.Customer;
import br.com.agibank.vendas.api.model.Item;
import br.com.agibank.vendas.api.model.Sale;
import br.com.agibank.vendas.api.model.SalesReport;
import br.com.agibank.vendas.api.model.Salesman;

public class SalesReportMapperTest {

	@Test
	public void returnSalesReportWhenValidLine() {
		List<Salesman> fileSalesman = new ArrayList<Salesman>();
		fileSalesman.add(Salesman.builder().cpf("73412508012").name("Jim Morrison").salary(new BigDecimal("140.00")).build());
		
		List<Customer> fileCustomers = new ArrayList<Customer>();
		fileCustomers.add(Customer.builder().cnpj("75998128000195").name("William Wallace").businessArea("HUMAN RESOURCE").build());
		
		List<Sale> fileSales = new ArrayList<Sale>();
		List<Item> itens = new ArrayList<Item>();
		itens.add(Item.builder().Id("13").quantity(new Integer(2)).price(new BigDecimal("10")).build());
		
		fileSales.add(Sale.builder().saleId("13").salesmanName("Jim Morrison").itemList(itens).build());
		
		SalesReport sales = SalesReportMapper.scanToSalesReport(fileSalesman, fileCustomers, fileSales);
		
		Assertions.assertNotNull(sales);
		Assertions.assertEquals(sales.getCustomersQuantity(), 1);
		Assertions.assertEquals(sales.getSalesmanQuantity(), 1);
		Assertions.assertEquals(sales.getMostExpensiveSaleId(),"13");
		Assertions.assertEquals(sales.getWorstSalesman(), "Jim Morrison");
	}
	
}
