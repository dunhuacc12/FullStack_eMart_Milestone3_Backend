package pers.chenxi.emart.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@RequestMapping(value = "/user", produces = "application/json")
	public Map<String, Object> user(OAuth2Authentication user) {
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("user", user.getUserAuthentication().getPrincipal());
		userInfo.put("authorities", user.getUserAuthentication().getAuthorities());
		return userInfo;
	}

	@GetMapping("/session/{userId}")
	public Map get(@PathVariable int userId) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("name", "test");
		map.put("desc", "test desc");
		return map;
	}

}
