package pers.chenxi.emart.common.repository;

import org.springframework.data.repository.CrudRepository;

import pers.chenxi.emart.common.entity.StoreEntity;

public interface StoreRepository extends CrudRepository<StoreEntity, Integer> {

	public StoreEntity findByOwnerIdAndDelFlg(String ownerId, String delFlg);

}
