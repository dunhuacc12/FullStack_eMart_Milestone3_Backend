package pers.chenxi.emart.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pers.chenxi.emart.common.entity.ItemsEntity;

/**
 * items repository.
 *
 * @author XiChen
 *
 */
public interface ItemsRepository extends CrudRepository<ItemsEntity, Integer> {

	/**
	 * find items by name.<br>
	 *
	 * findBy + porpName + Like
	 *
	 * @param searchContent
	 *            search content
	 * @return result
	 */
	public List<ItemsEntity> findByItemsNameLike(String searchContent);

	@Query("select t from ItemsEntity t where t.itemsName like CONCAT('%',:searchContent,'%')")
	public List<ItemsEntity> findItemsWithCustomQuery(@Param("searchContent") String searchContent);

	public Optional<ItemsEntity> findById(Integer id);

	@Modifying
	@Query("update ItemsEntity set stockNum = :stockNum where id = :itemId")
	public void updateStockNumber(@Param("itemId") Integer itemId, @Param("stockNum") Integer stockNum);

}
