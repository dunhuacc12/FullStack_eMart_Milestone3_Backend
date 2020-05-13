package pers.chenxi.emart.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * main class.
 *
 * @author XiChen
 */
@ComponentScan(basePackages = { "pers.chenxi.emart.product", "pers.chenxi.emart.common" })
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BackendEmartProductApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BackendEmartProductApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BackendEmartProductApplication.class);
	}
}
