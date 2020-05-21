package pers.chenxi.emart.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.chenxi.emart.common.constant.CodeConst;
import pers.chenxi.emart.common.entity.DiscountsEntity;
import pers.chenxi.emart.common.entity.TransactionsEntity;
import pers.chenxi.emart.common.repository.ShoppingCartRepository;
import pers.chenxi.emart.common.repository.TransactionsRepository;
import pers.chenxi.emart.order.constant.CodeConst4Order;
import pers.chenxi.emart.order.dto.CheckoutDto;
import pers.chenxi.emart.order.dto.ShoppingCartListItem;

/**
 * checkout service.
 *
 * @author XiChen
 *
 */
@Service
@Transactional
public class CheckoutService {

	@Autowired
	TransactionsRepository transactionsRepository;

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	/**
	 * checkout.
	 *
	 * @param checkoutDto
	 *            dto object
	 */
	public void checkout(CheckoutDto checkoutDto) {
		List<ShoppingCartListItem> products = checkoutDto.getProducts();

		if (products != null && products.size() > 0) {
			products.forEach(e -> {

				// insert Transactions
				TransactionsEntity entity = new TransactionsEntity();
				entity.setUserId(checkoutDto.getUserId());
				entity.setItemId(e.getItemId());

				DiscountsEntity discount = new DiscountsEntity();
				discount.setId(checkoutDto.getDiscountId());
				entity.setDiscounts(discount);

				entity.setNumber(e.getNo());
				entity.setTransactionStatus(CodeConst4Order.TRANSACTION_STATUS_PAID);
				entity.setCrtUserId(checkoutDto.getUserId());
				entity.setUpdUserId(checkoutDto.getUserId());
				entity.setDelFlg(CodeConst.DEL_FLG_NOT_DELETED);
				transactionsRepository.save(entity);

				// delete shopping cart
				shoppingCartRepository.deleteItem(checkoutDto.getUserId(), e.getItemId());
			});
		}
	}
}
