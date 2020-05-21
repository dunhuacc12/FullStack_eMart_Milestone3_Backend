package pers.chenxi.emart.product.controller;

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
import pers.chenxi.emart.product.dto.AddItemDto;
import pers.chenxi.emart.product.dto.DropdownDto;
import pers.chenxi.emart.product.service.AddItemService;

/**
 * add items controller.
 *
 * @author XiChen
 *
 */
@RestController
public class AddItemController {

	@Autowired
	AddItemService addItemService;

	/**
	 * search options of Category.
	 *
	 * @return result
	 */
	@GetMapping("/getCategoryOptions")
	public Object getCategoryOptions() {
		List<DropdownDto> searchRet = addItemService.getCategoryOptions();
		ResponseGeneric<List<DropdownDto>> res = new ResponseGeneric<>();
		res.setData(searchRet);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}

	/**
	 * search options of sub category.
	 *
	 * @param categoryValue
	 *            category id
	 * @return result
	 */
	@GetMapping("/getSubcategoryOptions/{categoryValue}")
	public Object getSubcategoryOptions(@PathVariable("categoryValue") Integer categoryValue) {
		List<DropdownDto> searchRet = addItemService.getSubcategoryOptions(categoryValue);
		ResponseGeneric<List<DropdownDto>> res = new ResponseGeneric<>();
		res.setData(searchRet);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}

	/**
	 * add item process.
	 *
	 * @param dto
	 *            dto object
	 */
	@PostMapping("/addItem")
	public Object addItem(@RequestBody AddItemDto dto) {
		addItemService.addItem(dto);
		ResponseGeneric<Object> res = new ResponseGeneric<>();
		res.setData(null);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}
}
