package pers.chenxi.emart.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * main class.
 *
 * @author XiChen
 *
 */
@ComponentScan(basePackages = { "pers.chenxi.emart.auth", "pers.chenxi.emart.common" })
@EnableJpaAuditing
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BackendEmartAuthApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BackendEmartAuthApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BackendEmartAuthApplication.class);
	}

}
