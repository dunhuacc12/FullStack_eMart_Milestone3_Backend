package pers.chenxi.emart.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pers.chenxi.emart.common.entity.TransactionsEntity;

public interface TransactionsRepository extends CrudRepository<TransactionsEntity, Integer> {

	public List<TransactionsEntity> findByUserIdAndDelFlg(String userId, String delFlg);

	public List<TransactionsEntity> findByItemIdIn(List<Integer> itemId);
}
