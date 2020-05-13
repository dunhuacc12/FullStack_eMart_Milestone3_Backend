package pers.chenxi.emart.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pers.chenxi.emart.auth.common.ResponseGeneric;
import pers.chenxi.emart.auth.dto.BuyerDto;
import pers.chenxi.emart.auth.dto.SellerDto;
import pers.chenxi.emart.auth.dto.UserDto;
import pers.chenxi.emart.auth.service.LoginService;

/**
 * login controller.
 *
 * @author XiChen
 */
@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	/**
	 * Login process.
	 *
	 * @param userId
	 *            user id
	 * @param user
	 *            user info
	 * @return login result
	 */
	@PostMapping("/session/{userid}")
	public Object login(@PathVariable("userid") String userId, @RequestBody UserDto user) {

		// search user info in buyer table
		BuyerDto buyer = loginService.checkBuyer(userId, user.getPassword());
		if (buyer != null) {
			ResponseGeneric<BuyerDto> res = new ResponseGeneric<>();
			res.setData(buyer);
			res.setStatus(HttpStatus.OK.value());
			return ResponseEntity.ok(res);
		}

		// search user info in seller table
		SellerDto seller = loginService.checkSeller(userId, user.getPassword());
		if (seller != null) {
			ResponseGeneric<SellerDto> res = new ResponseGeneric<>();
			res.setData(seller);
			res.setStatus(HttpStatus.OK.value());
			return ResponseEntity.ok(res);
		}

		// When the user does not exist
		ResponseGeneric<Object> res = new ResponseGeneric<>();
		res.setData(null);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}
}
