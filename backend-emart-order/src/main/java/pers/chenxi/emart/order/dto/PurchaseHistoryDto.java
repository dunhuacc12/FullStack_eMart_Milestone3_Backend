package pers.chenxi.emart.order.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class PurchaseHistoryDto {

	private Integer itemId;

	private String imgUrl;

	private String itemName;

	private BigDecimal price;

	private Integer no;

	private Date purchaseDate;
}
