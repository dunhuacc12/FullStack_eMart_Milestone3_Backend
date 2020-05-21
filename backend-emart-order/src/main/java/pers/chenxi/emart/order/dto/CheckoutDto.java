package pers.chenxi.emart.order.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CheckoutDto {

	List<ShoppingCartListItem> products = new ArrayList<>();

	private BigDecimal totalPrice;

	private BigDecimal tax;

	private BigDecimal discount;

	private Integer discountId;

	private String userId;

}
