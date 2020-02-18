package br.com.agibank.vendas.api.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataTypeEnum {
	VENDEDOR("001"),
	CLIENTE("002"),
	VENDA("003"),
	DESCONHECIDO("");
	
	private String value;
	
    public static DataTypeEnum fromCode(String value) {
        for (DataTypeEnum dataType : DataTypeEnum.values()) {
            if (dataType.getValue().equalsIgnoreCase(value)) {
                return dataType;
            }
        }
        return DESCONHECIDO;
    }
}
