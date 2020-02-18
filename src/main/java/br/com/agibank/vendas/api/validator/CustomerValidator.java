package br.com.agibank.vendas.api.validator;

import org.apache.commons.lang3.ObjectUtils;

public class CustomerValidator {
    public static Boolean validateCustomer(String cnpj, String name, String businessArea) {
        return CnpjCpfValidator.validateCnpj(cnpj) && ObjectUtils.allNotNull(name, businessArea);
    }
}
