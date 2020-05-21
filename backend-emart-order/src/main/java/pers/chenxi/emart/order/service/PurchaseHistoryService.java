package pers.chenxi.emart.order.service;

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

import lombok.extern.slf4j.Slf4j;
import pers.chenxi.emart.common.ResponseGeneric;
import pers.chenxi.emart.common.RestTemplateUtils;
import pers.chenxi.emart.common.constant.CodeConst;
import pers.chenxi.emart.common.dto.ItemInfoDto;
import pers.chenxi.emart.common.dto.TransactionInfoDto;
import pers.chenxi.emart.common.entity.TransactionsEntity;
import pers.chenxi.emart.common.repository.TransactionsRepository;
import pers.chenxi.emart.order.dto.PurchaseHistoryDto;

/**
 * purchase history service.
 *
 * @author XiChen
 *
 */
@Service
@Transactional
@Slf4j(topic = "product_log")
public class PurchaseHistoryService {

	@Autowired
	TransactionsRepository transactionsRepository;

	/**
	 * search purchase history.
	 *
	 * @param userId
	 *            user id
	 * @return result
	 * @throws JSONException
	 *             exception
	 */
	@SuppressWarnings("unchecked")
	public List<PurchaseHistoryDto> getPurchaseHistory(String userId) throws JSONException {
		List<PurchaseHistoryDto> ret = new ArrayList<>();
		// search purchase history
		List<TransactionsEntity> transactions = transactionsRepository.findByUserIdAndDelFlg(userId,
				CodeConst.DEL_FLG_NOT_DELETED);
		if (transactions != null && transactions.size() > 0) {

			JSONArray jsonArr = new JSONArray();

			transactions.forEach(e -> {
				jsonArr.put(e.getItemId());
			});

			// get item info
			JSONObject json = new JSONObject();
			json.put("ids", jsonArr);
			log.info(json.toString());
			String url = "http://localhost:8098/getItemInfoByIds";
			RestTemplate restTemplate = new RestTemplate();
			ResponseGeneric<List<LinkedHashMap<String, Object>>> response = restTemplate.postForObject(url,
					RestTemplateUtils.getJSONObj4Request(json.toString()), ResponseGeneric.class);
			if (HttpStatus.OK.value() == response.getStatus()) {
				List<LinkedHashMap<String, Object>> itemsMap = response.getData();
				List<ItemInfoDto> items = new ArrayList<>();
				itemsMap.forEach(map -> {
					String tmpStr = JSON.toJSONString(map);
					items.add(JSON.parseObject(tmpStr, ItemInfoDto.class));
				});
				if (!CollectionUtils.isEmpty(items)) {
					transactions.forEach(t -> {
						ItemInfoDto item = items.stream().filter(o -> o.getId() != null && o.getId() == t.getItemId())
								.findFirst().get();
						PurchaseHistoryDto dto = new PurchaseHistoryDto();
						dto.setItemId(item.getId());
						dto.setItemName(item.getItemsName());
						dto.setImgUrl(item.getImgUrl());
						dto.setPrice(item.getPrice());
						dto.setNo(t.getNumber());
						dto.setPurchaseDate(t.getTransactionDate());
						ret.add(dto);
					});
				}
			}
		}
		return ret;
	}

	/**
	 * search transaction history.
	 *
	 * @param itemIds
	 *            item id
	 * @return result
	 */
	public List<TransactionInfoDto> getTransactionHistoryByItemIds(List<Integer> itemIds) {
		List<TransactionInfoDto> ret = new ArrayList<>();
		List<TransactionsEntity> searchRet = transactionsRepository.findByItemIdIn(itemIds);
		if (!CollectionUtils.isEmpty(searchRet)) {
			searchRet.forEach(e -> {
				TransactionInfoDto dto = new TransactionInfoDto();
				dto.setUserId(e.getUserId());
				dto.setItemId(e.getItemId());
				dto.setNumber(e.getNumber());
				dto.setTransactionDate(e.getTransactionDate());
				dto.setTransactionStatus(e.getTransactionStatus());
				dto.setDiscountId(e.getDiscounts().getId());
				dto.setDiscountCode(e.getDiscounts().getDiscountCode());
				dto.setPercentage(e.getDiscounts().getPercentage());
				ret.add(dto);
			});
		}
		return ret;
	}
}
