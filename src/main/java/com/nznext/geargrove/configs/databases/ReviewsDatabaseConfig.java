package com.nznext.geargrove.configs.databases;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = ReviewsDatabaseConfig.ENTITY_MANAGER_FACTORY,
        transactionManagerRef = ReviewsDatabaseConfig.TRANSACTION_MANAGER,
        basePackages = ReviewsDatabaseConfig.JPA_REPOSITORY_PACKAGE
)
@EntityScan(basePackages = "com.nznext.geargrove.reviews.entities")
public class ReviewsDatabaseConfig {
    public static final String PROPERTY_PREFIX = "geargrove.reviews.datasource";
    public static final String JPA_REPOSITORY_PACKAGE = "com.nznext.geargrove.reviews.repositories";
    public static final String ENTITY_PACKAGE = "com.nznext.geargrove.reviews.entities";
    public static final String ENTITY_MANAGER_FACTORY = "reviewsEntityManagerFactory";
    public static final String DATA_SOURCE = "reviewsDataSource";
    public static final String DATABASE_PROPERTY = "reviewsDatabaseProperty";
    public static final String TRANSACTION_MANAGER = "reviewsTransactionManager";

    @Bean(DATABASE_PROPERTY)
    @ConfigurationProperties(prefix = PROPERTY_PREFIX)
    public DatabaseProperty appdatabaseProperty() {
        return new DatabaseProperty();
    }

    @Bean(DATA_SOURCE)
    public DataSource  appDataSource(
            @Qualifier(DATABASE_PROPERTY) DatabaseProperty databaseProperty
    ) {
        return DataSourceBuilder
                .create()
                .username(databaseProperty.getUsername())
                .password(databaseProperty.getPassword())
                .url(databaseProperty.getUrl())
                .driverClassName(databaseProperty.getDriverClassName())
                .build();
    }

    @Bean(ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean appEntityManager(
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource);
        entityManager.setPersistenceUnitName(ENTITY_MANAGER_FACTORY);
        entityManager.setPackagesToScan(ENTITY_PACKAGE);
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.validation.mode", "none");
        properties.put("hibernate.hbm2ddl.auto", "update");
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }

    @Bean(TRANSACTION_MANAGER)
    public PlatformTransactionManager sqlSessionTemplate(
            @Qualifier(ENTITY_MANAGER_FACTORY) LocalContainerEntityManagerFactoryBean entityManager,
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager.getObject());
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
