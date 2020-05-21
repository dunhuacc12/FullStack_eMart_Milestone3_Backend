package pers.chenxi.emart.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pers.chenxi.emart.auth.dto.BuyerDto;
import pers.chenxi.emart.auth.dto.SellerDto;
import pers.chenxi.emart.auth.service.SignupService;
import pers.chenxi.emart.common.ResponseGeneric;

/**
 * sign up controller.
 *
 * @author XiChen
 *
 */
@RestController
public class SignupController {

	@Autowired
	SignupService signupService;

	/**
	 * seller sign up.
	 *
	 * @param sellerDto
	 *            seller dto
	 * @return result
	 */
	@PostMapping("/signup-seller")
	public Object sellerSignup(@RequestBody SellerDto sellerDto) {
		signupService.sellerSignup(sellerDto);
		ResponseGeneric<Object> res = new ResponseGeneric<>();
		res.setData(null);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}

	/**
	 * buyer sign up.
	 *
	 * @param sellerDto
	 *            seller dto
	 * @return result
	 */
	@PostMapping("/signup-buyer")
	public Object buyerSignup(@RequestBody BuyerDto buyerDto) {
		signupService.buyerSignup(buyerDto);
		ResponseGeneric<Object> res = new ResponseGeneric<>();
		res.setData(null);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}
}
