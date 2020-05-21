package pers.chenxi.emart.product.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SellerReportListDto {

	private Integer itemId;

	private String itemName;

	private String imgUrl;

	private Integer numberofSales;

	private BigDecimal totalAmount;
}
