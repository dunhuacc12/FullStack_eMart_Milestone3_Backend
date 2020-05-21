package pers.chenxi.emart.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pers.chenxi.emart.common.ResponseGeneric;
import pers.chenxi.emart.product.dto.SellerReportDto;
import pers.chenxi.emart.product.dto.SellerReportListDto;
import pers.chenxi.emart.product.service.SellerReportService;

/**
 * seller report controller.
 *
 * @author XiChen
 *
 */
@RestController
public class SellerReportController {

	@Autowired
	SellerReportService sellerReportService;

	/**
	 * get seller report.
	 *
	 * @param dto
	 *            dto object
	 * @return result
	 * @throws JSONException
	 *             exception
	 */
	@PostMapping("/getSellerReport")
	public Object getSellerReport(@RequestBody SellerReportDto dto) throws JSONException {
		List<SellerReportListDto> searchRet = sellerReportService.getSellerReport(dto);
		ResponseGeneric<List<SellerReportListDto>> res = new ResponseGeneric<>();
		res.setData(searchRet);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}
}
