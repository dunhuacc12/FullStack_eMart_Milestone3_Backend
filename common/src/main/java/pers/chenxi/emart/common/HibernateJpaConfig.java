package pers.chenxi.emart.common;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hibernate JPA Config.
 *
 * @author XiChen
 */
@Configuration
@EntityScan(basePackages = "pers.chenxi.emart.common.entity")
@EnableJpaRepositories(basePackages = "pers.chenxi.emart.common.repository")
@EnableTransactionManagement
public class HibernateJpaConfig {

}