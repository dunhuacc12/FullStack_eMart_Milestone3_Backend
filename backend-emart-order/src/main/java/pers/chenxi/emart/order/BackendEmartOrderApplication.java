package pers.chenxi.emart.order;

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
 */
@ComponentScan(basePackages = { "pers.chenxi.emart.order", "pers.chenxi.emart.common" })
@EnableJpaAuditing
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BackendEmartOrderApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BackendEmartOrderApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BackendEmartOrderApplication.class);
	}
}
