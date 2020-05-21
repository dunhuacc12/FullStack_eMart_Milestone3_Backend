package pers.chenxi.emart.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pers.chenxi.emart.common.entity.SubCategoryEntity;

public interface SubCategoryRepository extends CrudRepository< SubCategoryEntity, Integer> {

	public List<SubCategoryEntity> findByCategoryId(Integer categoryId);

}
