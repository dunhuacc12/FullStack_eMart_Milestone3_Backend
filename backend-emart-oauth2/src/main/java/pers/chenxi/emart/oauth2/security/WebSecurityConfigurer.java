package pers.chenxi.emart.oauth2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	// UsernamePasswordAuthenticationFilter user;
	DelegatingFilterProxy proxy;
	// SecurityContextHolderAwareRequestFilter a;
	// JdbcUserDetailsManager d;

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
		auth.inMemoryAuthentication() // 认证信息存储到内存中
				.passwordEncoder(passwordEncoder()).withUser("user").password(passwordEncoder().encode("123456"))
				.roles("ADMIN", "USER", "ANONYMOUS");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests()
		// .antMatchers("/auth/**").permitAll()//.anyRequest().hasAnyRole("ANONYMOUS,
		// USER")
		// .antMatchers(HttpMethod.OPTIONS).permitAll()
		// .anyRequest().authenticated()
		// .and()
		// .httpBasic()
		// .and()
		// .csrf().disable();
		http.csrf().disable().authorizeRequests().antMatchers("/user").permitAll().antMatchers("/**").authenticated();
	}

	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
