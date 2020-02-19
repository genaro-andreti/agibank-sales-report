package br.com.agibank.vendas.api.mapper;

import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import br.com.agibank.vendas.api.model.Item;
import br.com.agibank.vendas.api.model.Sale;
import br.com.agibank.vendas.api.validator.SaleValidator;

public class SaleMapper {
    public static Sale scanToSale(Scanner scan) {
        if (!ObjectUtils.isEmpty(scan)) {
            String saleId = scan.hasNext() ? scan.next() : null;
            String itemList = scan.hasNext() ? scan.next() : null;
            String salesmanName = scan.hasNext() ? scan.next() : null;
            if (!SaleValidator.validateSale(saleId, itemList, salesmanName)) {
                return null;
            }

            if (Objects.nonNull(saleId) && Objects.nonNull(itemList) && Objects.nonNull(salesmanName)) {
                List<Item> saleItems = getSaleItems(itemList.replaceAll(" ", ""));
                return Sale.builder()
                        .saleId(saleId)
                        .itemList(saleItems)
                        .salesmanName(salesmanName)
                        .build();
            }
        }
        return null;
    }

    private static List<Item> getSaleItems(String itemList) {
        List<Item> saleItems = new ArrayList<>();
        itemList = itemList.substring(1, itemList.length() -1);
        String[] items = itemList.split(",");
        for (String item : items) {
            try (Scanner scan = new Scanner(item).useDelimiter("-")) {
                String itemId = scan.hasNext() ? scan.next() : null;
                String itemQtd = scan.hasNext() ? scan.next() : null;
                String itemPrice = scan.hasNext() ? scan.next() : null;
                if (Objects.nonNull(itemId) && Objects.nonNull(itemQtd) && Objects.nonNull(itemPrice)) {
                    saleItems.add(buildItem(itemId, itemQtd, itemPrice));
                }
            }
        }
        return saleItems;
    }

    private static Item buildItem(String itemId, String itemQtd, String itemPrice) {
        return Item.builder()
                .Id(itemId)
                .quantity(Integer.valueOf(itemQtd))
                .price(new BigDecimal(itemPrice))
                .build();
    }
}
