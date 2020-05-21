package pers.chenxi.emart.order.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ShoppingCartListItem {

	private Integer itemId;

	private String imgUrl;

	private String itemName;

	private BigDecimal price;

	private Integer no;
}
