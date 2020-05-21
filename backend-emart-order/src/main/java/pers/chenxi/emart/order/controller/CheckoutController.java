package pers.chenxi.emart.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pers.chenxi.emart.common.ResponseGeneric;
import pers.chenxi.emart.order.dto.CheckoutDto;
import pers.chenxi.emart.order.service.CheckoutService;

/**
 * checkout controller.
 *
 * @author XiChen
 *
 */
@RestController
public class CheckoutController {

	@Autowired
	CheckoutService checkoutService;

	@PostMapping("/checkout")
	public Object checkout(@RequestBody CheckoutDto checkoutDto) {
		checkoutService.checkout(checkoutDto);
		ResponseGeneric<Object> res = new ResponseGeneric<>();
		res.setData(null);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}
}
