package pers.chenxi.emart.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import pers.chenxi.emart.common.entity.ItemsEntity;
import pers.chenxi.emart.common.repository.ItemsRepository;
import pers.chenxi.emart.product.dto.SearchResultDto;

/**
 * search items service.
 *
 * @author XiChen
 *
 */
@Service
@Transactional
public class SearchItemsService {

	@Autowired
	ItemsRepository itemsRepository;

	/**
	 * find items by name.
	 *
	 * @param searchContent
	 *            search content
	 * @return result
	 */
	public List<SearchResultDto> searchItems(String searchContent) {

		List<ItemsEntity> searchRets = itemsRepository.findItemsWithCustomQuery(searchContent);
		List<SearchResultDto> searchResultDtoList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(searchRets)) {
			searchRets.forEach(e -> {
				SearchResultDto dto = new SearchResultDto();
				dto.setItemId(e.getId().toString());
				dto.setItemName(e.getItemsName());
				dto.setPrice(e.getPrice());
				dto.setImgUrl(e.getImgUrl());
				dto.setStoreName(e.getStoreItems().getStore().getStoreName());
				searchResultDtoList.add(dto);
			});
		}
		return searchResultDtoList;
	}
}
