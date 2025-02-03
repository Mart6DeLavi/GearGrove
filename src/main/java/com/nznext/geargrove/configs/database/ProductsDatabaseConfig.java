package com.nznext.geargrove.configs.database;

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

/**
 * Configuration class for setting up the database connection and JPA settings
 * for the products module in the GearGrove application.
 *
 * <p>This class configures the beans related to the database connection,
 * entity management, and transaction management for the products module,
 * which includes:</p>
 * <ul>
 *   <li>Database properties configuration</li>
 *   <li>Data source setup</li>
 *   <li>Entity manager factory</li>
 *   <li>Transaction management</li>
 * </ul>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Configuration} - Marks this class as a Spring configuration class.</li>
 *   <li>{@code @EnableJpaRepositories} - Enables JPA repositories for the products module.</li>
 *   <li>{@code @EntityScan} - Specifies the package for entity scanning related to the products module.</li>
 * </ul>
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = ProductsDatabaseConfig.ENTITY_MANAGER_FACTORY,
        transactionManagerRef = ProductsDatabaseConfig.TRANSACTION_MANAGER,
        basePackages = ProductsDatabaseConfig.JPA_REPOSITORY_PACKAGE
)
@EntityScan(basePackages = "com.nznext.geargrove.login.entities")
public class ProductsDatabaseConfig {

    /** Prefix for database properties in the application configuration file. */
    public static final String PROPERTY_PREFIX = "geargrove.products.datasource";

    /** Base package for JPA repositories related to the products module. */
    public static final String JPA_REPOSITORY_PACKAGE = "com.nznext.geargrove.products.repositories";

    /** Base package for entity classes related to the products module. */
    public static final String ENTITY_PACKAGE = "com.nznext.geargrove.products.entities";

    /** Bean name for the entity manager factory. */
    public static final String ENTITY_MANAGER_FACTORY = "productsEntityManagerFactory";

    /** Bean name for the data source. */
    public static final String DATA_SOURCE = "productsDataSource";

    /** Bean name for the database property configuration. */
    public static final String DATABASE_PROPERTY = "productsDatabaseProperty";

    /** Bean name for the transaction manager. */
    public static final String TRANSACTION_MANAGER = "productsTransactionManager";


    /**
     * Defines a {@link DatabaseProperty} bean that reads database configuration
     * properties from the application configuration file.
     *
     * @return a configured {@link DatabaseProperty} instance containing the database connection settings.
     */
    @Bean(DATABASE_PROPERTY)
    @ConfigurationProperties(prefix = PROPERTY_PREFIX)
    public DatabaseProperty appDatabaseProperty() {
        return new DatabaseProperty();
    }


    /**
     * Creates a {@link DataSource} bean for managing database connections.
     *
     * <p>This method initializes the data source using properties from
     * {@link DatabaseProperty}.</p>
     *
     * @param databaseProperty the database configuration properties.
     * @return a configured {@link DataSource} instance.
     */
    @Bean(DATA_SOURCE)
    public DataSource appDataSource(
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

    /**
     * Configures the {@link LocalContainerEntityManagerFactoryBean} bean for JPA entity management.
     *
     * <p>This method sets the data source, package scanning for entities,
     * and JPA properties including Hibernate settings.</p>
     *
     * @param dataSource the configured {@link DataSource}.
     * @return a configured {@link LocalContainerEntityManagerFactoryBean}.
     */
    @Bean(ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean appEntityManager(
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPersistenceUnitName(ENTITY_MANAGER_FACTORY);
        em.setPackagesToScan(ENTITY_PACKAGE);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.validation.mode", "none");
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);
        return em;
    }

    /**
     * Creates a {@link PlatformTransactionManager} bean for managing JPA transactions.
     *
     * <p>This transaction manager is responsible for handling database transactions
     * using the configured entity manager and data source.</p>
     *
     * @param entityManager the configured {@link LocalContainerEntityManagerFactoryBean}.
     * @param dataSource    the configured {@link DataSource}.
     * @return a configured {@link PlatformTransactionManager}.
     */
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
