package pers.chenxi.emart.product.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MyStoreListItemDto {

	private Integer itemId;

	private String imgUrl;

	private String itemName;

	private BigDecimal price;

	private String status;

	private Integer stockNumber;
}
