package pers.chenxi.emart.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pers.chenxi.emart.common.ResponseGeneric;
import pers.chenxi.emart.product.dto.ProductDetailDto;
import pers.chenxi.emart.product.service.ProductDetailService;

/**
 * product detail controller.
 *
 * @author XiChen
 *
 */
@RestController
public class ProductDetailController {

	@Autowired
	ProductDetailService productDetailService;

	/**
	 * get product detail by product id.
	 *
	 * @param productId
	 *            product id
	 * @return result
	 */
	@GetMapping("/productDetail/{productId}")
	public Object getProductDetail(@PathVariable("productId") Integer productId) {
		ProductDetailDto searchRet = productDetailService.getProductDetail(productId);
		ResponseGeneric<ProductDetailDto> res = new ResponseGeneric<>();
		res.setData(searchRet);
		res.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(res);
	}
}
