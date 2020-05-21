package pers.chenxi.emart.product.dto;

import java.util.List;

import lombok.Data;

@Data
public class MyStoreDto {

	private String userId;

	private String storeName;

	private List<MyStoreListItemDto> products;

}
