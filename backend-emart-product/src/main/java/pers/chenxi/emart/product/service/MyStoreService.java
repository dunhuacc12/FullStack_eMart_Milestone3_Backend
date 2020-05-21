package pers.chenxi.emart.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import pers.chenxi.emart.common.constant.CodeConst;
import pers.chenxi.emart.common.entity.StoreEntity;
import pers.chenxi.emart.common.entity.StoreItemsEntity;
import pers.chenxi.emart.common.repository.ItemsRepository;
import pers.chenxi.emart.common.repository.StoreRepository;
import pers.chenxi.emart.product.dto.MyStoreDto;
import pers.chenxi.emart.product.dto.MyStoreListItemDto;

/**
 * my store service.
 *
 * @author XiChen
 *
 */
@Service
@Transactional
public class MyStoreService {

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	ItemsRepository itemsRepository;

	/**
	 * search store info by user id.
	 *
	 * @param userId
	 *            user id
	 * @return store info
	 */
	public MyStoreDto getMyStoreInfo(String userId) {
		MyStoreDto dto = new MyStoreDto();
		// search my store info
		StoreEntity entity = storeRepository.findByOwnerIdAndDelFlg(userId, CodeConst.DEL_FLG_NOT_DELETED);
		if (entity != null) {
			dto.setUserId(userId);
			dto.setStoreName(entity.getStoreName());
			// set Items on sale
			List<MyStoreListItemDto> myStoreList = new ArrayList<>();
			List<StoreItemsEntity> storeItems = entity.getStoreItems();
			if (!CollectionUtils.isEmpty(storeItems)) {
				storeItems.forEach(e -> {
					MyStoreListItemDto listItem = new MyStoreListItemDto();
					listItem.setItemId(e.getItem().getId());
					listItem.setItemName(e.getItem().getItemsName());
					listItem.setImgUrl(e.getItem().getImgUrl());
					listItem.setPrice(e.getItem().getPrice());
					listItem.setStockNumber(e.getItem().getStockNum());
					listItem.setStatus(e.getItem().getStockNum() != null && e.getItem().getStockNum() > 0 ? "On sell"
							: "Sold out");
					myStoreList.add(listItem);
				});
			}
			dto.setProducts(myStoreList);
		}
		return dto;
	}

	/**
	 * update stock number.
	 *
	 * @param dto
	 *            dto object
	 */
	public void updateStockNumber(MyStoreListItemDto dto) {
		itemsRepository.updateStockNumber(dto.getItemId(), dto.getStockNumber());
	}
}
