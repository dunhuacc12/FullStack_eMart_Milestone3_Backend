package pers.chenxi.emart.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pers.chenxi.emart.common.entity.BuyerEntity;
import pers.chenxi.emart.common.entity.SellerEntity;
import pers.chenxi.emart.common.repository.BuyerRepository;
import pers.chenxi.emart.common.repository.SellerRepository;

public class AuthUserDetailsService implements UserDetailsService {

	private BuyerRepository buyerRepository;

	private SellerRepository sellerRepository;

	public AuthUserDetailsService(BuyerRepository buyerRepository, SellerRepository sellerRepository) {
		this.buyerRepository = buyerRepository;
		this.sellerRepository = sellerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		BuyerEntity buyerEntity = buyerRepository.findUserByName(username);
		if (buyerEntity != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("ROLE_BUYER"));
			return new User(buyerEntity.getUserId(), passwordEncoder().encode(buyerEntity.getPassword()), authorities);
		}

		SellerEntity sellerEntity = sellerRepository.findUserByName(username);
		if (sellerEntity != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
			return new User(sellerEntity.getUserId(), passwordEncoder().encode(sellerEntity.getPassword()),
					authorities);
		}
		return null;
	}

	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
