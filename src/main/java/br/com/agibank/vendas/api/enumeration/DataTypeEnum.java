package br.com.agibank.vendas.api.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataTypeEnum {
	SALESMAN("001"),
	CUSTOMER("002"),
	SALE("003"),
	UNKNOW("");
	
	private String value;
	
    public static DataTypeEnum fromValue(String value) {
        for (DataTypeEnum dataType : DataTypeEnum.values()) {
            if (dataType.getValue().equalsIgnoreCase(value)) {
                return dataType;
            }
        }
        return UNKNOW;
    }
}
