package pers.chenxi.emart.product.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

import pers.chenxi.emart.common.ResponseGeneric;
import pers.chenxi.emart.common.RestTemplateUtils;
import pers.chenxi.emart.common.constant.CodeConst;
import pers.chenxi.emart.common.dto.TransactionInfoDto;
import pers.chenxi.emart.common.entity.StoreEntity;
import pers.chenxi.emart.common.entity.StoreItemsEntity;
import pers.chenxi.emart.common.repository.StoreRepository;
import pers.chenxi.emart.product.dto.SellerReportDto;
import pers.chenxi.emart.product.dto.SellerReportListDto;

/**
 * seller report service.
 *
 * @author XiChen
 *
 */
@Service
@Transactional
public class SellerReportService {

	@Autowired
	StoreRepository storeRepository;

	/**
	 * get seller report.
	 *
	 * @param dto
	 *            dto object
	 * @return result
	 * @throws JSONException
	 *             exception
	 */
	@SuppressWarnings("unchecked")
	public List<SellerReportListDto> getSellerReport(SellerReportDto paramDto) throws JSONException {
		List<SellerReportListDto> ret = new ArrayList<>();
		StoreEntity entity = storeRepository.findByOwnerIdAndDelFlg(paramDto.getUserId(),
				CodeConst.DEL_FLG_NOT_DELETED);
		List<StoreItemsEntity> storeitems = entity.getStoreItems();
		if (!CollectionUtils.isEmpty(storeitems)) {
			// search orders
			JSONArray jsonArr = new JSONArray();
			storeitems.forEach(e -> {
				jsonArr.put(e.getItem().getId());
			});

			// get transaction history info
			JSONObject json = new JSONObject();
			json.put("ids", jsonArr);
			String url = "http://localhost:8097/getTransactionHistoryByItemIds";
			RestTemplate restTemplate = new RestTemplate();
			ResponseGeneric<List<LinkedHashMap<String, Object>>> response = restTemplate.postForObject(url,
					RestTemplateUtils.getJSONObj4Request(json.toString()), ResponseGeneric.class);
			if (HttpStatus.OK.value() == response.getStatus()) {
				List<LinkedHashMap<String, Object>> itemsMap = response.getData();
				List<TransactionInfoDto> transactions = new ArrayList<>();
				itemsMap.forEach(map -> {
					String tmpStr = JSON.toJSONString(map);
					transactions.add(JSON.parseObject(tmpStr, TransactionInfoDto.class));
				});
				if (!CollectionUtils.isEmpty(transactions)) {

					// edit transaction history
					transactions.forEach(t -> {
						SellerReportListDto dto = new SellerReportListDto();
						StoreItemsEntity storeItem = storeitems.stream()
								.filter(e -> t.getItemId() == e.getItem().getId()).findFirst().get();

						dto.setItemId(storeItem.getItem().getId());
						dto.setImgUrl(storeItem.getItem().getImgUrl());
						dto.setNumberofSales(t.getNumber());
						dto.setItemName(storeItem.getItem().getItemsName());
						dto.setTotalAmount(t.getNumber() != null && storeItem.getItem().getPrice() != null
								? storeItem.getItem().getPrice().multiply(new BigDecimal(t.getNumber()))
								: BigDecimal.ZERO);
						ret.add(dto);
					});

					// storeitems.forEach(e -> {
					// SellerReportListDto dto = new SellerReportListDto();
					//
					// TransactionInfoDto transactionInfoDto = transactions.stream()
					// .filter(t -> t.getItemId() != null && t.getItemId() ==
					// e.getItem().getId()).findFirst()
					// .get();
					// dto.setItemId(e.getItem().getId());
					// dto.setImgUrl(e.getItem().getImgUrl());
					// dto.setNumberofSales(transactionInfoDto.getNumber());
					// dto.setItemName(e.getItem().getItemsName());
					// dto.setTotalAmount(transactionInfoDto.getNumber() != null &&
					// e.getItem().getPrice() != null
					// ? e.getItem().getPrice().multiply(new
					// BigDecimal(transactionInfoDto.getNumber()))
					// : BigDecimal.ZERO);
					// ret.add(dto);
					// });
				}
			}
		}
		return ret;
	}
}
