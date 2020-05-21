package pers.chenxi.emart.common.repository;

import org.springframework.data.repository.CrudRepository;

import pers.chenxi.emart.common.entity.DiscountsEntity;

public interface DiscountsRepository extends CrudRepository<DiscountsEntity, Integer> {

	public DiscountsEntity findByDiscountCode(String discountCode);
}
