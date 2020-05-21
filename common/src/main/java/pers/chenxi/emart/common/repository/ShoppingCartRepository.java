package pers.chenxi.emart.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pers.chenxi.emart.common.entity.ShoppingCartEntity;
import pers.chenxi.emart.common.resultSet.ShoppingCartItemsResultSet;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCartEntity, Integer> {

	@Query(value = "select new pers.chenxi.emart.common.resultSet.ShoppingCartItemsResultSet(s.itemId, i.price, i.itemsName, i.imgUrl, s.number) from ShoppingCartEntity s , ItemsEntity i where s.userId = :userId and s.itemId = i.id and i.delFlg = '0' and s.delFlg = '0' ")
	public List<ShoppingCartItemsResultSet> getShoppingCartListByUserId(@Param("userId") String userId);

	@Modifying
	@Query("update ShoppingCartEntity set delFlg = '1' where userId = :userId and itemId = :itemId")
	public void deleteItem(@Param("userId") String userId, @Param("itemId") Integer itemId);

}
