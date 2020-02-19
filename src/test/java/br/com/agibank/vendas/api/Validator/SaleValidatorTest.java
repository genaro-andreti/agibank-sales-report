package br.com.agibank.vendas.api.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.agibank.vendas.api.validator.SaleValidator;

public class SaleValidatorTest {

	@Test
	public void returnTrueWhenValidSaleData() {
		String saleId = "13";
		String items = "[13-2-10, 5-1-10]";
		String salesmanName = "Jim Morrison";
		Boolean isValidSale = SaleValidator.validateSale(saleId, items, salesmanName);
		Assertions.assertTrue(isValidSale);

	}

	@Test
	public void returnFalseWhenNoSalesmanName() {
		String saleId = "13";
		String items = "[13-2-10, 5-1-10]";
		String salesmanName = null;
		Boolean isValidSale = SaleValidator.validateSale(saleId, items, salesmanName);
		Assertions.assertFalse(isValidSale);
	}

	@Test
	public void returnFalseWhenNoSaleItem() {
		String saleId = "13";
		String items = null;
		String salesmanName = "Jim Morrison";
		Boolean isValidSale = SaleValidator.validateSale(saleId, items, salesmanName);
		Assertions.assertFalse(isValidSale);
	}

	@Test
	public void returnFalseWhenNoSaleId() {
		String saleId = null;
		String items = "[13-2-10, 5-1-10]";
		String salesmanName = "Jim Morrison";
		Boolean isValidSale = SaleValidator.validateSale(saleId, items, salesmanName);
		Assertions.assertFalse(isValidSale);
	}

	@Test
	public void returnFalseWhenNoData() {
		String saleId = null;
		String items = null;
		String salesmanName = null;
		Boolean isValidSale = SaleValidator.validateSale(saleId, items, salesmanName);
		Assertions.assertFalse(isValidSale);
	}

}
