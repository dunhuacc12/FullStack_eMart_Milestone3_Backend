package pers.chenxi.emart.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pers.chenxi.emart.common.ResponseGeneric;
import pers.chenxi.emart.common.dto.IdsDto;
import pers.chenxi.emart.common.dto.TransactionInfoDto;
import pers.chenxi.emart.order.dto.PurchaseHistoryDto;
import pers.chenxi.emart.order.service.PurchaseHistoryService;

/**
 * purchase history controller.
 *
 * @author XiChen
 *
 */
@RestController
public class PurchaseHistoryController {

	@Autowired
	PurchaseHistoryService purchaseHistoryService;

	/**
	 * search purchase history
	 *
	 * @param userId
	 *            user id
	 * @return result
	 * @throws JSONException
	 *             exception
	 */
	@GetMapping("/getPurchaseHistory/{userid}")
	public Object getPurchaseHistory(@PathVariable("userid") String userId) throws JSONException {
		List<PurchaseHistoryDto> searchRet = purchaseHistoryService.getPurchaseHistory(userId);
		ResponseGeneric<List<PurchaseHistoryDto>> res = new ResponseGeneric<>();
		res.setData(searchRet);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}

	/**
	 * search transaction history.
	 *
	 * @param jsonObj
	 *            JSON object
	 * @return result
	 * @throws JSONException
	 *             exception
	 */
	@PostMapping("/getTransactionHistoryByItemIds")
	public Object getTransactionHistoryByItemIds(@RequestBody IdsDto idsDto) throws JSONException {
		List<Integer> itemIds = idsDto.getIds();
		List<TransactionInfoDto> searchRet = purchaseHistoryService.getTransactionHistoryByItemIds(itemIds);
		ResponseGeneric<List<TransactionInfoDto>> res = new ResponseGeneric<>();
		res.setData(searchRet);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}
}
