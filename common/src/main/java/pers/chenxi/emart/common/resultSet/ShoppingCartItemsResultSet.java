package pers.chenxi.emart.common.resultSet;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingCartItemsResultSet {

	private Integer itemId;

	private BigDecimal price;

	private String itemsName;

	private String imgUrl;

	private Integer number;
}
