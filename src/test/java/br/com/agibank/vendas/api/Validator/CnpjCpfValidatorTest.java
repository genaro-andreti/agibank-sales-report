package br.com.agibank.vendas.api.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.agibank.vendas.api.validator.CnpjCpfValidator;

public class CnpjCpfValidatorTest {

	@Test
	public void returnTrueWhenValidCnpj() {
		String cnpj = "75998128000195";
		Boolean isValid = CnpjCpfValidator.validateCnpj(cnpj);
		Assertions.assertTrue(isValid);
	}

	@Test
	public void returnFalseWhenCnpjSmaller() {
		String cnpj = "7599812800019";
		Boolean isValid = CnpjCpfValidator.validateCnpj(cnpj);
		Assertions.assertFalse(isValid);
	}

	@Test
	public void returnFalseWhenCnpjBigger() {
		String cnpj = "759981280001950";
		Boolean isValid = CnpjCpfValidator.validateCnpj(cnpj);
		Assertions.assertFalse(isValid);
	}

	@Test
	public void returnFalseWhenCnpjWithLetters() {
		String cnpj = "759981280001aa";
		Boolean isValid = CnpjCpfValidator.validateCnpj(cnpj);
		Assertions.assertFalse(isValid);
	}

	@Test
	public void returnTrueWhenValidCpf() {
		String cpf = "73412508012";
		Boolean isValid = CnpjCpfValidator.validateCpf(cpf);
		Assertions.assertTrue(isValid);
	}

	@Test
	public void returnFalseWhenCpfSmaller() {
		String cpf = "7341250801";
		Boolean isValid = CnpjCpfValidator.validateCpf(cpf);
		Assertions.assertFalse(isValid);
	}

	@Test
	public void returnFalseWhenCpfBigger() {
		String cpf = "734125080120";
		Boolean isValid = CnpjCpfValidator.validateCpf(cpf);
		Assertions.assertFalse(isValid);
	}

	@Test
	public void returnFalseWhenCpfWithLetters() {
		String cpf = "734125080aa";
		Boolean isValid = CnpjCpfValidator.validateCpf(cpf);
		Assertions.assertFalse(isValid);
	}

}
