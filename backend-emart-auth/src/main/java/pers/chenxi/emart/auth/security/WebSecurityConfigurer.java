package pers.chenxi.emart.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pers.chenxi.emart.auth.service.AuthUserDetailsService;
import pers.chenxi.emart.common.repository.BuyerRepository;
import pers.chenxi.emart.common.repository.SellerRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	BuyerRepository buyerRepository;

	@Autowired
	SellerRepository sellerRepository;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
		// .withUser("user")
		// .password(passwordEncoder().encode("user"))
		// .roles("USER")
		// .and()
		// .withUser("admin")
		// .password(passwordEncoder().encode("admin"))
		// .roles("ADMIN", "USER", "ANONYMOUS");
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(new AuthUserDetailsService(buyerRepository, sellerRepository));
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
