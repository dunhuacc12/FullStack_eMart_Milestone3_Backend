package pers.chenxi.emart.user.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.chenxi.emart.common.constant.CodeConst;
import pers.chenxi.emart.common.dto.BuyerDto;
import pers.chenxi.emart.common.dto.SellerDto;
import pers.chenxi.emart.common.entity.BuyerEntity;
import pers.chenxi.emart.common.entity.SellerEntity;
import pers.chenxi.emart.common.repository.BuyerRepository;
import pers.chenxi.emart.common.repository.SellerRepository;
import pers.chenxi.emart.user.constant.CodeConst4Auth;

/**
 * sign up service.
 *
 * @author XiChen
 *
 */
@Service
@Transactional
public class SignupService {

	@Autowired
	SellerRepository sellerRepository;
	@Autowired
	BuyerRepository buyerRepository;

	/**
	 * seller sign up.
	 *
	 * @param sellerDto
	 *            seller dto
	 */
	public void sellerSignup(SellerDto sellerDto) {
		SellerEntity sellerEntity = new SellerEntity();
		BeanUtils.copyProperties(sellerDto, sellerEntity);
		sellerEntity.setCrtUserId(sellerDto.getUserId());
		sellerEntity.setUpdUserId(sellerDto.getUserId());
		sellerEntity.setStatus(CodeConst4Auth.USER_STATUS_NOT_LOGGED_IN);
		sellerEntity.setDelFlg(CodeConst.DEL_FLG_NOT_DELETED);
		sellerRepository.save(sellerEntity);
	}

	/**
	 * buyer sign up.
	 *
	 * @param buyerDto
	 *            buyer dto
	 */
	public void buyerSignup(BuyerDto buyerDto) {
		BuyerEntity buyerEntity = new BuyerEntity();
		BeanUtils.copyProperties(buyerDto, buyerEntity);
		buyerEntity.setCrtUserId(buyerDto.getUserId());
		buyerEntity.setUpdUserId(buyerDto.getUserId());
		buyerEntity.setStatus(CodeConst4Auth.USER_STATUS_NOT_LOGGED_IN);
		buyerEntity.setDelFlg(CodeConst.DEL_FLG_NOT_DELETED);
		buyerRepository.save(buyerEntity);
	}
}
