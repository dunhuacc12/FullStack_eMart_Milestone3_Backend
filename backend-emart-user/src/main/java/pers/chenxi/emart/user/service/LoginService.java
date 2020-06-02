package pers.chenxi.emart.user.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.chenxi.emart.common.dto.BuyerDto;
import pers.chenxi.emart.common.dto.SellerDto;
import pers.chenxi.emart.common.entity.BuyerEntity;
import pers.chenxi.emart.common.entity.SellerEntity;
import pers.chenxi.emart.common.repository.BuyerRepository;
import pers.chenxi.emart.common.repository.SellerRepository;

/**
 * user service.
 *
 * @author XiChen
 */
@Service
@Transactional
public class LoginService {

	@Autowired
	BuyerRepository buyerRepository;

	@Autowired
	SellerRepository sellerRepository;

	/**
	 * check login user is a buyer.
	 *
	 * @param userId
	 *            user id
	 * @param password
	 *            password
	 * @return check result
	 */
	public BuyerDto checkBuyer(String userId, String password) {
		BuyerEntity buyerEntity = buyerRepository.findUserByIdAndPassword(userId, password);
		if (buyerEntity != null) {
			BuyerDto buyer = new BuyerDto();
			BeanUtils.copyProperties(buyerEntity, buyer);
			return buyer;
		}
		return null;
	}

	/**
	 * check login user is a seller.
	 *
	 * @param userId
	 *            user id
	 * @param password
	 *            password
	 * @return check result
	 */
	public SellerDto checkSeller(String userId, String password) {
		SellerEntity sellerEntity = sellerRepository.findUserByIdAndPassword(userId, password);
		if (sellerEntity != null) {
			SellerDto seller = new SellerDto();
			BeanUtils.copyProperties(sellerEntity, seller);
			return seller;
		}
		return null;
	}
}
