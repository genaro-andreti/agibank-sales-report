package br.com.agibank.vendas.api.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.agibank.vendas.api.validator.SalesmanValidator;

public class SalesmanValidatorTest {

	@Test
	public void returnTrueWhenValidSalesmanData() {
		String cpf = "73412508012";
		String name = "Jim Morrison";
		String salary = "140.00";
		Boolean isValidSalesman = SalesmanValidator.validateSalesman(cpf, name, salary);
		Assertions.assertTrue(isValidSalesman);
	}

	@Test
	public void returnFalseWhenNoSalesmanName() {
		String cpf = "73412508012";
		String name = null;
		String salary = "140.00";
		Boolean isValidSalesman = SalesmanValidator.validateSalesman(cpf, name, salary);
		Assertions.assertFalse(isValidSalesman);
	}

	@Test
	public void returnFalseWhenNoSalesmanSalary() {
		String cpf = "73412508012";
		String name = "Jim Morrison";
		String salary = null;
		Boolean isValidSalesman = SalesmanValidator.validateSalesman(cpf, name, salary);
		Assertions.assertFalse(isValidSalesman);
	}

	@Test
	public void returnFalseWhenNoSalaryAndName() {
		String cpf = "73412508012";
		String name = null;
		String salary = null;
		Boolean isValidSalesman = SalesmanValidator.validateSalesman(cpf, name, salary);
		Assertions.assertFalse(isValidSalesman);
	}

	@Test
	public void returnFalseWhenSmallerCpf() {
		String cpf = "7341250801";
		String name = "Jim Morrison";
		String salary = "140.00";
		Boolean isValidSalesman = SalesmanValidator.validateSalesman(cpf, name, salary);
		Assertions.assertFalse(isValidSalesman);
	}

	@Test
	public void returnFalseWhenBiggerCpf() {
		String cpf = "734125080120";
		String name = "Jim Morrison";
		String salary = "140.00";
		Boolean isValidSalesman = SalesmanValidator.validateSalesman(cpf, name, salary);
		Assertions.assertFalse(isValidSalesman);
	}

	@Test
	public void returnFalseWhenCpfWithLetter() {
		String cpf = "7341250801aa";
		String name = "Jim Morrison";
		String salary = "140.00";
		Boolean isValidSalesman = SalesmanValidator.validateSalesman(cpf, name, salary);
		Assertions.assertFalse(isValidSalesman);
	}

	@Test
	public void returnFalseWhenInvalidSalary() {
		String cpf = "73412508012";
		String name = "Jim Morrison";
		String salary = "140ab";
		Boolean isValidSalesman = SalesmanValidator.validateSalesman(cpf, name, salary);
		Assertions.assertFalse(isValidSalesman);
	}

}
