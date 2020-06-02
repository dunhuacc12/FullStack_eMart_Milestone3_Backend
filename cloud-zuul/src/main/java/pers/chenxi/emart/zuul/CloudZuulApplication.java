package pers.chenxi.emart.zuul;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

import pers.chenxi.emart.zuul.filter.AccessFilter;
import pers.chenxi.emart.zuul.util.UserContextInterceptor;

@EnableZuulProxy
@SpringBootApplication
@EnableOAuth2Client
public class CloudZuulApplication {

	@Bean
	public AccessFilter accessFilter(){
		return new AccessFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudZuulApplication.class, args);
	}

	@Bean
	@Primary
	public RestTemplate getRestTemplate() {
		RestTemplate template = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
		if (interceptors == null) {
			interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		}

		interceptors.add(new UserContextInterceptor());
		template.setInterceptors(interceptors);
		return template;
	}

}
