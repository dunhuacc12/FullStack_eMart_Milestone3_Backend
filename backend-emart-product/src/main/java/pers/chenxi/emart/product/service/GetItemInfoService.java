package pers.chenxi.emart.product.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.chenxi.emart.common.dto.ItemInfoDto;
import pers.chenxi.emart.common.entity.ItemsEntity;
import pers.chenxi.emart.common.repository.ItemsRepository;

/**
 * get item info.
 *
 * @author XiChen
 *
 */
@Service
@Transactional
public class GetItemInfoService {

	@Autowired
	ItemsRepository itemsRepository;

	public List<ItemInfoDto> getItemInfoByIds(List<Integer> ids) {
		List<ItemInfoDto> ret = new ArrayList<>();
		Iterable<ItemsEntity> searchRet = itemsRepository.findAllById(ids);
		Iterator<ItemsEntity> it = searchRet.iterator();
		while (it.hasNext()) {
			ItemsEntity entity = it.next();
			ItemInfoDto dto = new ItemInfoDto();
			dto.setId(entity.getId());
			dto.setItemsName(entity.getItemsName());
			dto.setCategoryId(entity.getCategoryId());
			dto.setSubCategoryId(entity.getSubCategoryId());
			dto.setImgUrl(entity.getImgUrl());
			dto.setPrice(entity.getPrice());
			dto.setStockNum(entity.getStockNum());
			dto.setRemark(entity.getRemark());
			ret.add(dto);
		}
		return ret;
	}
}
