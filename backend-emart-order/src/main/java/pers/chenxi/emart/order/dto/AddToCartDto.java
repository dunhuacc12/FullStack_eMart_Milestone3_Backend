package pers.chenxi.emart.order.dto;

import lombok.Data;

@Data
public class AddToCartDto {

	private String userId;

	private Integer itemId;
}
