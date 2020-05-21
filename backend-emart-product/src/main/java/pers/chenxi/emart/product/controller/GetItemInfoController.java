package pers.chenxi.emart.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pers.chenxi.emart.common.ResponseGeneric;
import pers.chenxi.emart.common.dto.IdsDto;
import pers.chenxi.emart.common.dto.ItemInfoDto;
import pers.chenxi.emart.product.service.GetItemInfoService;

/**
 * get item controller
 *
 * @author XiChen
 *
 */
@RestController
public class GetItemInfoController {

	@Autowired
	GetItemInfoService getItemInfoService;

	/**
	 * get items info by id
	 *
	 * @param itemIds
	 *            item id
	 * @return result
	 */
	@PostMapping("/getItemInfoByIds")
	public Object getItemInfo(@RequestBody IdsDto idsDto) {
		List<Integer> itemIds = idsDto.getIds();
		List<ItemInfoDto> ret = getItemInfoService.getItemInfoByIds(itemIds);
		ResponseGeneric<List<ItemInfoDto>> res = new ResponseGeneric<>();
		res.setData(ret);
		res.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseGeneric<List<ItemInfoDto>>>(res, HttpStatus.OK);
	}

}
