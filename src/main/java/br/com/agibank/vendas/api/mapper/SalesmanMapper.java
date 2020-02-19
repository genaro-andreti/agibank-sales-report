package br.com.agibank.vendas.api.mapper;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.util.ObjectUtils;

import br.com.agibank.vendas.api.model.Salesman;
import br.com.agibank.vendas.api.validator.SalesmanValidator;

public class SalesmanMapper {
	
    public static Salesman scanToSalesman(Scanner scanner) {
        if (!ObjectUtils.isEmpty(scanner) && scanner.hasNext()) {
            String cpf = scanner.next();
            String name = scanner.hasNext() ? scanner.next() : null;
            String salary = scanner.hasNext() ? scanner.next() : null;
            if (!SalesmanValidator.validateSalesman(cpf, name, salary)) {
                return null;
            }
            return Salesman.builder()
                    .cpf(cpf)
                    .name(name)
                    .salary(Optional.ofNullable(salary).map(BigDecimal::new).orElse(null))
                    .build();
        } else {
            return null;
        }
    }

}
