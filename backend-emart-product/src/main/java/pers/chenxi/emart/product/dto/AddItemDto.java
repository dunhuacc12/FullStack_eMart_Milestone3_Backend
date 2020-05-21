package pers.chenxi.emart.product.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class AddItemDto {

	private String userId;

	private Integer categoryValue;

	private String categoryLabel;

	private Integer subcategoryValue;

	private String subcategoryLabel;

	private String itemName;

	private BigDecimal price;

	private Integer stockNumber;

	private List<DescriptionDto> descriptions;

}
