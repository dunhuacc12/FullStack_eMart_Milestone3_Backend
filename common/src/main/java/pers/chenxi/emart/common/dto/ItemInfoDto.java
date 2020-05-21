package pers.chenxi.emart.common.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemInfoDto {

	private Integer id;

	private Integer categoryId;

	private Integer subCategoryId;

	private BigDecimal price;

	private String itemsName;

	private String imgUrl;

	private Integer stockNum;

	private String remark;

}
