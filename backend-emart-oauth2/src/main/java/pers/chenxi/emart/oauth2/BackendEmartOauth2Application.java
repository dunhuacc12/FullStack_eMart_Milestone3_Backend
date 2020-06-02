package pers.chenxi.emart.oauth2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableAuthorizationServer
public class BackendEmartOauth2Application {

	private static final String KEY_USER = "user";
	private static final String KEY_AUTHORITIES = "authorities";

	@RequestMapping(value= {"/user"}, produces="application/json")
	public Map<String, Object> auth(OAuth2Authentication user){
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put(KEY_USER, user.getUserAuthentication());
		userInfo.put(KEY_AUTHORITIES, AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));

		return userInfo;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendEmartOauth2Application.class, args);
	}

}
