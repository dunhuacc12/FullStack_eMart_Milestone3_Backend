package pers.chenxi.emart.product.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ProductDetailDto {

	private String productId;

	private List<String> imgUrl;

	private String productName;

	private BigDecimal price;

	private String storeName;

	private List<String> descriptions;

}
