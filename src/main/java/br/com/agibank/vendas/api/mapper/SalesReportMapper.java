package br.com.agibank.vendas.api.mapper;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

import br.com.agibank.vendas.api.model.Customer;
import br.com.agibank.vendas.api.model.Sale;
import br.com.agibank.vendas.api.model.SalesReport;
import br.com.agibank.vendas.api.model.Salesman;

public class SalesReportMapper {
	public static SalesReport scanToSalesReport(List<Salesman> fileSalesman, List<Customer> fileCustomers,
			List<Sale> fileSales) {
		Sale highestSale = getBiggestSale(fileSales);
		return SalesReport.builder().customersQuantity(fileCustomers.size()).salesmanQuantity(fileSalesman.size())
				.mostExpensiveSaleId(highestSale.getSaleId()).worstSalesman(getWorstSalesman(fileSales)).build();
	}

	private static Sale getBiggestSale(List<Sale> sales) {
		return sales.stream().max(Comparator.comparing(Sale::getSaleTotalAmount))
				.orElse(Sale.builder().saleId("").build());
	}

	private static String getWorstSalesman(List<Sale> sales) {
		return sales.stream().map(Sale::getSalesmanName).distinct().map(getSalesmanWithSaleAmount(sales))
				.min(Comparator.comparing(Pair::getValue)).map(Pair::getKey).orElse("");
	}

	private static Function<String, Pair<String, BigDecimal>> getSalesmanWithSaleAmount(List<Sale> sales) {
		return (String salesmanName) -> {
			BigDecimal salesmanSaleAmount = sales.stream()
					.filter(sale -> salesmanName.equalsIgnoreCase(sale.getSalesmanName())).map(Sale::getSaleTotalAmount)
					.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
			return Pair.of(salesmanName, salesmanSaleAmount);
		};
	}
}
