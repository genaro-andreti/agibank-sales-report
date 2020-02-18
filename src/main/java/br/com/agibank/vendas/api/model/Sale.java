package br.com.agibank.vendas.api.model;

import java.math.BigDecimal;
import java.util.List;

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
public class Sale {

    private String saleId;
    private List<Item> itemList;
    private String salesmanName;
    
    public BigDecimal getSaleTotalAmount() {
        return itemList.stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
