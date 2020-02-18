package br.com.agibank.vendas.api.validator;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class SalesmanValidator {
    public static Boolean validateSalesman(String cpf, String name, String salary) {
        return CnpjCpfValidator.validateCpf(cpf) &&
                ObjectUtils.allNotNull(name, salary) && NumberUtils.isCreatable(salary);
    }
}
