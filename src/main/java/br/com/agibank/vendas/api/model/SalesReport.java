package br.com.agibank.vendas.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesReport {

    private Integer customersQuantity;
    private Integer salesmanQuantity;
    private String mostExpensiveSaleId;
    private String worstSalesman;
}
