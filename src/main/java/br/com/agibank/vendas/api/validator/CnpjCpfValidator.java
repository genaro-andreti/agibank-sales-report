package br.com.agibank.vendas.api.validator;

import org.apache.commons.lang3.math.NumberUtils;

public class CnpjCpfValidator {
    public static Boolean validateCpf(String cpf) {
        return NumberUtils. isCreatable(cpf) && cpf.length() == 11;
    }

    public static Boolean validateCnpj(String cnpj) {
        return NumberUtils.isCreatable(cnpj) && cnpj.length() == 14;
    }
}
