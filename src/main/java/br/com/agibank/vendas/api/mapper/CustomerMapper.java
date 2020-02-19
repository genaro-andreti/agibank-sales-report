package br.com.agibank.vendas.api.mapper;

import java.util.Scanner;

import org.springframework.util.ObjectUtils;

import br.com.agibank.vendas.api.model.Customer;
import br.com.agibank.vendas.api.validator.CustomerValidator;

public class CustomerMapper {
	
    public static Customer scanToCustomer(Scanner scan) {
        if (!ObjectUtils.isEmpty(scan) && scan.hasNext()) {
            String cnpj = scan.next();
            String nome = scan.hasNext() ? scan.next() : null;
            String businessArea = scan.hasNext() ? scan.next() : null;

            if (!CustomerValidator.validateCustomer(cnpj, nome, businessArea)) {
                return null;
            }

            return Customer.builder()
                    .cnpj(cnpj)
                    .name(nome)
                    .businessArea(businessArea)
                    .build();
        } else {
            return null;
        }
    }

}
