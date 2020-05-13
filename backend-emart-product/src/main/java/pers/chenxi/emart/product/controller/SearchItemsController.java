package pers.chenxi.emart.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pers.chenxi.emart.product.common.ResponseGeneric;
import pers.chenxi.emart.product.dto.SearchResultDto;
import pers.chenxi.emart.product.service.SearchItemsService;

/**
 * search items controller.
 *
 * @author XiChen
 *
 */
@RestController
public class SearchItemsController {

	@Autowired
	SearchItemsService searchItemsService;

	/**
	 * search process.
	 *
	 * @param searchContent
	 *            search content
	 * @return result
	 */
	@GetMapping("/searchItems/{searchContent}")
	public Object searchItems(@PathVariable("searchContent") String searchContent) {

		List<SearchResultDto> searchRet = searchItemsService.searchItems(searchContent);
		ResponseGeneric<List<SearchResultDto>> res = new ResponseGeneric<>();
		res.setData(searchRet);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}
}
