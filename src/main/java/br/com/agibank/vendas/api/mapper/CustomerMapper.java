package br.com.agibank.vendas.api.mapper;

import java.util.Scanner;

import org.apache.commons.lang3.ObjectUtils;

import br.com.agibank.vendas.api.model.Customer;
import br.com.agibank.vendas.api.validator.CustomerValidator;

public class CustomerMapper {
    public static Customer mapperFromScanner(Scanner scan) {
        if (!ObjectUtils.isEmpty(scan) && scan.hasNext()) {
            String cnpj = scan.next();
            String name = scan.hasNext() ? scan.next() : null;
            String businessArea = scan.hasNext() ? scan.next() : null;

            if (!CustomerValidator.validateCustomer(cnpj, name, businessArea)) {
                return null;
            }

            return Customer.builder()
                    .cnpj(cnpj)
                    .name(name)
                    .businessArea(businessArea)
                    .build();
        } else {
            return null;
        }
    }
}
