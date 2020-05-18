package pers.chenxi.emart.product.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.chenxi.emart.common.entity.DescriptionEntity;
import pers.chenxi.emart.common.entity.ImgUrlEntity;
import pers.chenxi.emart.common.entity.ItemsEntity;
import pers.chenxi.emart.common.repository.ItemsRepository;
import pers.chenxi.emart.product.dto.ProductDetailDto;

/**
 * Product Detail Service.
 *
 * @author XiChen
 *
 */
@Service
@Transactional
public class ProductDetailService {

	@Autowired
	ItemsRepository itemsRepository;

	public ProductDetailDto getProductDetail(Integer id) {
		ProductDetailDto dto = new ProductDetailDto();
		Optional<ItemsEntity> selRet = itemsRepository.findById(id);
		selRet.ifPresent(p -> {
			dto.setProductId(p.getId().toString());
			dto.setPrice(p.getPrice());
			dto.setProductName(p.getItemsName());
			dto.setStoreName(p.getStoreItems().getStore().getStoreName());
			// image URL
			Set<ImgUrlEntity> imgUrls = p.getImgUrls4Detail();
			if (imgUrls != null && imgUrls.size() > 0) {
				dto.setImgUrl(imgUrls.stream().sorted(Comparator.comparing(ImgUrlEntity::getSort))
						.map(ImgUrlEntity::getImgUrls).collect(Collectors.toList()));
			}

			// description
			Set<DescriptionEntity> descriptions = p.getDescriptions();
			if (descriptions != null && descriptions.size() > 0) {

				List<String> descriptionLst = descriptions.stream()
						.sorted(Comparator.comparing(DescriptionEntity::getSort))
						.map(k -> k.getDescriptionLabel() + " : " + k.getDescriptionValue())
						.collect(Collectors.toList());
				dto.setDescriptions(descriptionLst);
			}
		});
		return dto;
	}
}
