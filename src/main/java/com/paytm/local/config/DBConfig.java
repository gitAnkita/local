package com.paytm.local.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import com.codahale.metrics.MetricRegistry;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.ConnectionReleaseMode;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef="localEntityManagerFactory",
		transactionManagerRef="localTransactionManager",
		basePackages= {"com.paytm.local.datasource.repository"})

public class DBConfig {

	@Bean(name = "metricRegistry")
	public MetricRegistry metricRegistry() {
		MetricRegistry metricRegistry = new MetricRegistry();
		return metricRegistry;
	}

	@Primary
	@Bean(name = "localHikariConfig")
	@ConfigurationProperties(prefix = "spring.datasource")
	public HikariConfig hikariConfig(@Qualifier("metricRegistry") MetricRegistry metricRegistry) {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setMetricRegistry(metricRegistry);
		return hikariConfig;
	}

	@Primary
	@Bean(name = "localDataSource")
	public DataSource dataSource(@Qualifier("localHikariConfig") HikariConfig hikariConfig) {
		return  new HikariDataSource(hikariConfig);
	}

	@Primary
	@Bean(name = "localEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	EntityManagerFactoryBuilder builder,
	@Qualifier("localDataSource") DataSource dataSource) {
	return builder.dataSource(dataSource)

		.packages("com.paytm.local.datasource.model")
			.properties(jpaProperties())
		.build();
	}

	protected Map<String, Object> jpaProperties() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
		props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
		props.put("hibernate.naming-strategy", ImprovedNamingStrategy.class.getName());
		props.put("hibernate.connection.release_mode", ConnectionReleaseMode.AFTER_TRANSACTION);
		props.put("hibernate.connection.handling_mode", "DELAYED_ACQUISITION_AND_RELEASE_AFTER_TRANSACTION");
//		props.put("hibernate.connection.provider_disables_autocommit",true);
		return props;
	}

	@Bean(name = "localTransactionManager")
	public PlatformTransactionManager transactionManager(
	@Qualifier("localEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
	return new JpaTransactionManager(entityManagerFactory);
	}

}
