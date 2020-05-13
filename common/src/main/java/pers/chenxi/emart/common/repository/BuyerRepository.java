package pers.chenxi.emart.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pers.chenxi.emart.common.entity.BuyerEntity;

/**
 * buyer repository.
 *
 * @author XiChen
 *
 */
@Repository
public interface BuyerRepository extends CrudRepository<BuyerEntity, Integer> {

	@Query("select u from BuyerEntity u where u.userId=:userName")
	public BuyerEntity findUserByName(@Param("userName") String userName);

	@Query("select u from BuyerEntity u where u.userId=:userId and u.password=:password")
	public BuyerEntity findUserByIdAndPassword(@Param("userId") String userId, @Param("password") String password);
}
