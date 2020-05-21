package pers.chenxi.emart.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pers.chenxi.emart.common.ResponseGeneric;
import pers.chenxi.emart.product.dto.MyStoreDto;
import pers.chenxi.emart.product.dto.MyStoreListItemDto;
import pers.chenxi.emart.product.service.MyStoreService;

/**
 * my store controller.
 *
 * @author XiChen
 *
 */
@RestController
public class MyStoreController {

	@Autowired
	MyStoreService myStoreService;

	/**
	 * search store info by user id
	 *
	 * @param userId
	 *            user id
	 * @return store info
	 */
	@GetMapping("/mystore/{userId}")
	public Object getMyStoreInfo(@PathVariable("userId") String userId) {
		MyStoreDto searchRet = myStoreService.getMyStoreInfo(userId);
		ResponseGeneric<MyStoreDto> res = new ResponseGeneric<>();
		res.setData(searchRet);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}

	/**
	 * update stock number.
	 *
	 * @param dto
	 *            dto object
	 * @return result
	 */
	@PostMapping("/updateStockNumber")
	public Object updateStockNumber(@RequestBody MyStoreListItemDto dto) {
		myStoreService.updateStockNumber(dto);
		ResponseGeneric<Object> res = new ResponseGeneric<>();
		res.setData(null);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}
}
