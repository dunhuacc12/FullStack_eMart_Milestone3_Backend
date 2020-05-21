package pers.chenxi.emart.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.chenxi.emart.common.constant.CodeConst;
import pers.chenxi.emart.common.entity.DiscountsEntity;
import pers.chenxi.emart.common.entity.ShoppingCartEntity;
import pers.chenxi.emart.common.repository.DiscountsRepository;
import pers.chenxi.emart.common.repository.ShoppingCartRepository;
import pers.chenxi.emart.common.resultSet.ShoppingCartItemsResultSet;
import pers.chenxi.emart.order.dto.AddToCartDto;
import pers.chenxi.emart.order.dto.DiscountDto;
import pers.chenxi.emart.order.dto.ShoppingCartListItem;

@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	DiscountsRepository discountsRepository;

	/**
	 * add to cart.
	 *
	 * @param dto
	 *            dto for add to cart
	 */
	public void addToCart(AddToCartDto dto) {
		ShoppingCartEntity entity = new ShoppingCartEntity();
		entity.setUserId(dto.getUserId());
		entity.setItemId(dto.getItemId());
		entity.setNumber(1);
		entity.setCrtUserId(dto.getUserId());
		entity.setUpdUserId(dto.getUserId());
		entity.setDelFlg(CodeConst.DEL_FLG_NOT_DELETED);
		shoppingCartRepository.save(entity);
	}

	/**
	 * get shopping cart.
	 *
	 * @param userId
	 *            user id
	 * @return list
	 */
	public List<ShoppingCartListItem> getShoppingCartList(String userId) {
		List<ShoppingCartListItem> ret = new ArrayList<>();
		List<ShoppingCartItemsResultSet> shoppingList = shoppingCartRepository.getShoppingCartListByUserId(userId);
		if (shoppingList != null && shoppingList.size() > 0) {
			shoppingList.forEach(e -> {
				ShoppingCartListItem item = new ShoppingCartListItem();
				item.setItemId(e.getItemId());
				item.setItemName(e.getItemsName());
				item.setImgUrl(e.getImgUrl());
				item.setPrice(e.getPrice());
				item.setNo(e.getNumber());
				ret.add(item);
			});
		}
		return ret;
	}

	/**
	 * get discount.
	 *
	 * @param discountCode
	 *            discount code
	 * @return result
	 */
	public DiscountDto getDiscount(String discountCode) {
		DiscountDto dto = new DiscountDto();
		DiscountsEntity entity = discountsRepository.findByDiscountCode(discountCode);
		if (entity != null) {
			dto.setDiscountId(entity.getId());
			dto.setDiscountCode(entity.getDiscountCode());
			dto.setPercentage(entity.getPercentage());
		}
		return dto;
	}
}
