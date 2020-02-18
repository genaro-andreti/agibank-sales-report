package br.com.agibank.vendas.api.validator;

import org.apache.commons.lang3.ObjectUtils;

public class SaleValidator {
    public static Boolean validateSale(String saleId, String items, String salesmanName) {
        return ObjectUtils.allNotNull(saleId, items, salesmanName);
    }
}
