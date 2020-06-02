package pers.chenxi.emart.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * main class.
 *
 * @author XiChen
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "pers.chenxi.emart.auth", "pers.chenxi.emart.common" })
@EnableResourceServer
@EnableEurekaClient
@EnableAuthorizationServer
public class BackendEmartAuthApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BackendEmartAuthApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BackendEmartAuthApplication.class);
	}

}
