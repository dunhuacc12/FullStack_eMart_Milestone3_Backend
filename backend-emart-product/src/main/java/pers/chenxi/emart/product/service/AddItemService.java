package pers.chenxi.emart.product.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import pers.chenxi.emart.common.constant.CodeConst;
import pers.chenxi.emart.common.entity.CategoryEntity;
import pers.chenxi.emart.common.entity.DescriptionEntity;
import pers.chenxi.emart.common.entity.ImgUrlEntity;
import pers.chenxi.emart.common.entity.ItemsEntity;
import pers.chenxi.emart.common.entity.StoreEntity;
import pers.chenxi.emart.common.entity.StoreItemsEntity;
import pers.chenxi.emart.common.entity.SubCategoryEntity;
import pers.chenxi.emart.common.repository.CategoryRepository;
import pers.chenxi.emart.common.repository.ImgUrlRepository;
import pers.chenxi.emart.common.repository.ItemsRepository;
import pers.chenxi.emart.common.repository.StoreRepository;
import pers.chenxi.emart.common.repository.SubCategoryRepository;
import pers.chenxi.emart.common.util.LambdaUtils;
import pers.chenxi.emart.product.dto.AddItemDto;
import pers.chenxi.emart.product.dto.DescriptionDto;
import pers.chenxi.emart.product.dto.DropdownDto;

/**
 * add items service.
 *
 * @author XiChen
 *
 */
@Service
@Transactional
public class AddItemService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Autowired
	ImgUrlRepository imgUrlRepository;

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	ItemsRepository itemsRepository;

	/**
	 * search options of Category.
	 *
	 * @return result
	 */
	public List<DropdownDto> getCategoryOptions() {
		List<DropdownDto> ret = new ArrayList<>();
		Iterable<CategoryEntity> options = categoryRepository.findAll();
		Iterator<CategoryEntity> it = options.iterator();
		while (it.hasNext()) {
			CategoryEntity entity = it.next();
			DropdownDto dto = new DropdownDto();
			dto.setValue(entity.getId());
			dto.setLabel(entity.getCategoryName());
			ret.add(dto);
		}
		return ret;
	}

	/**
	 * search options of sub category.
	 *
	 * @param categoryValue
	 *            category id
	 * @return result
	 */
	public List<DropdownDto> getSubcategoryOptions(Integer categoryValue) {
		List<DropdownDto> ret = new ArrayList<>();
		List<SubCategoryEntity> options = subCategoryRepository.findByCategoryId(categoryValue);
		if (!CollectionUtils.isEmpty(options)) {
			options.forEach(e -> {
				DropdownDto dto = new DropdownDto();
				dto.setValue(e.getId());
				dto.setLabel(e.getSubCategoryName());
				ret.add(dto);
			});
		}
		return ret;
	}

	/**
	 * add item process.
	 *
	 * @param dto
	 *            dto object
	 */
	public void addItem(AddItemDto dto) {
		// item
		ItemsEntity itemsEntity = new ItemsEntity();
		itemsEntity.setItemsName(dto.getItemName());
		itemsEntity.setCategoryId(dto.getCategoryValue());
		itemsEntity.setSubCategoryId(dto.getSubcategoryValue());
		itemsEntity.setPrice(dto.getPrice());
		itemsEntity.setStockNum(dto.getStockNumber());
		itemsEntity.setCrtUserId(dto.getUserId());
		itemsEntity.setUpdUserId(dto.getUserId());
		itemsEntity.setDelFlg(CodeConst.DEL_FLG_NOT_DELETED);

		// descriptions
		Set<DescriptionEntity> descriptionEntitySet = new HashSet<>();
		List<DescriptionDto> descriptions = dto.getDescriptions();
		if (!CollectionUtils.isEmpty(descriptions)) {
			descriptions.forEach(LambdaUtils.consumerWithIndex((e, idx) -> {
				if (!StringUtils.isEmpty(e.getDescriptionValue()) || !StringUtils.isEmpty(e.getDescriptionLabel())) {
					DescriptionEntity entity = new DescriptionEntity();
					entity.setDescriptionValue(e.getDescriptionValue());
					entity.setDescriptionLabel(e.getDescriptionLabel());
					entity.setSort(idx + 1);
					entity.setCrtUserId(dto.getUserId());
					entity.setUpdUserId(dto.getUserId());
					entity.setDelFlg(CodeConst.DEL_FLG_NOT_DELETED);
					entity.setItem(itemsEntity);
					descriptionEntitySet.add(entity);
				}
			}));
		}

		// image URL
		// TODO The image file should be saved on S3 of AWS, so the URL should be
		// obtained after accessing an API.
		// Now set the URL of the image to a fixed value, which is a temporary way.
		Set<ImgUrlEntity> imgUrls4DetailSet = new HashSet<>();
		Iterable<ImgUrlEntity> existingImgUrls = imgUrlRepository.findAll();
		Iterator<ImgUrlEntity> it = existingImgUrls.iterator();
		int i = 0;
		while (it.hasNext()) {
			ImgUrlEntity exist = it.next();

			ImgUrlEntity entity = new ImgUrlEntity();
			entity.setImgUrls(exist.getImgUrls());
			entity.setSort(i + 1);
			entity.setCrtUserId(dto.getUserId());
			entity.setUpdUserId(dto.getUserId());
			entity.setDelFlg(CodeConst.DEL_FLG_NOT_DELETED);
			entity.setItem(itemsEntity);
			imgUrls4DetailSet.add(entity);
			if (i >= 3) {
				break;
			}
			i++;
		}

		// search store info by user id
		StoreEntity storeInfo = storeRepository.findByOwnerIdAndDelFlg(dto.getUserId(), CodeConst.DEL_FLG_NOT_DELETED);
		// Table of stores and products
		StoreItemsEntity storeItems = new StoreItemsEntity();
		storeItems.setStore(storeInfo);
		storeItems.setItem(itemsEntity);
		storeItems.setCrtUserId(dto.getUserId());
		storeItems.setUpdUserId(dto.getUserId());
		storeItems.setDelFlg(CodeConst.DEL_FLG_NOT_DELETED);

		itemsEntity.setDescriptions(descriptionEntitySet);
		itemsEntity.setImgUrl(CollectionUtils.isEmpty(imgUrls4DetailSet) ? null
				: imgUrls4DetailSet.stream().findFirst().get().getImgUrls());
		itemsEntity.setImgUrls4Detail(imgUrls4DetailSet);
		itemsEntity.setStoreItems(storeItems);
		itemsRepository.save(itemsEntity);
	}
}
