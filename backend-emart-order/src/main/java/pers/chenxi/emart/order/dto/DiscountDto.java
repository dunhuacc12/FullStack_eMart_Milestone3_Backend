package pers.chenxi.emart.order.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DiscountDto {

	private Integer discountId;

	private String discountCode;

	private BigDecimal percentage;
}
