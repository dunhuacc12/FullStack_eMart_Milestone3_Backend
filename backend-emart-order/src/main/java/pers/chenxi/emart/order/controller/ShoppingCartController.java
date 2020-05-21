package pers.chenxi.emart.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pers.chenxi.emart.common.ResponseGeneric;
import pers.chenxi.emart.order.dto.AddToCartDto;
import pers.chenxi.emart.order.dto.DiscountDto;
import pers.chenxi.emart.order.dto.ShoppingCartListItem;
import pers.chenxi.emart.order.service.ShoppingCartService;

/**
 * shopping cart controller.
 *
 * @author XiChen
 *
 */
@RestController
public class ShoppingCartController {

	@Autowired
	ShoppingCartService shoppingCartService;

	/**
	 * add the product to shopping cart.
	 *
	 * @param dto
	 *            dto object
	 * @return result
	 */
	@PostMapping("/addToCart")
	public Object addToCart(@RequestBody AddToCartDto dto) {
		shoppingCartService.addToCart(dto);
		ResponseGeneric<Object> res = new ResponseGeneric<>();
		res.setData(null);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}

	/**
	 * get shopping cart item list.
	 *
	 * @param userId
	 *            user id
	 * @return result
	 */
	@GetMapping("/getShoppingList/{userid}")
	public Object getShoppingCartList(@PathVariable("userid") String userId) {
		List<ShoppingCartListItem> searchRet = shoppingCartService.getShoppingCartList(userId);
		ResponseGeneric<List<ShoppingCartListItem>> res = new ResponseGeneric<>();
		res.setData(searchRet);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}

	/**
	 * get discount amount.
	 *
	 * @param discountCode
	 *            discount code
	 * @return amount
	 */
	@GetMapping("/getDiscount/{discountCode}")
	public Object getDiscount(@PathVariable("discountCode") String discountCode) {
		DiscountDto searchRet = shoppingCartService.getDiscount(discountCode);
		ResponseGeneric<DiscountDto> res = new ResponseGeneric<>();
		res.setData(searchRet);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}
}
