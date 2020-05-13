package pers.chenxi.emart.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pers.chenxi.emart.common.entity.SellerEntity;

/**
 * seller repository.
 *
 * @author XiChen
 *
 */
public interface SellerRepository extends CrudRepository<SellerEntity, Integer> {

	@Query("select u from SellerEntity u where u.userId=:userId and u.password=:password")
	public SellerEntity findUserByIdAndPassword(@Param("userId") String userId, @Param("password") String password);

}
