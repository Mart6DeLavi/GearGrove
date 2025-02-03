package com.nznext.geargrove.configs.database;

import lombok.Getter;
import lombok.Setter;

/**
 * The {@code DatabaseProperty} class holds configuration properties
 * for connecting to a database. It includes essential details
 * such as the database URL, username, password, and driver class name.
 *
 * <p>This class is typically used to store database connection details
 * that can be injected from a configuration file or environment variables.</p>
 *
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @Getter} - Automatically generates getter methods for all fields.</li>
 *   <li>{@code @Setter} - Automatically generates setter methods for all fields.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 * DatabaseProperty dbProperty = new DatabaseProperty();
 * dbProperty.setUrl("jdbc:postgresql://localhost:5432/mydb");
 * dbProperty.setUsername("admin");
 * dbProperty.setPassword("secret");
 * dbProperty.setDriverClassName("org.postgresql.Driver");
 * </pre>
 *
 * <p>This class is commonly used in conjunction with Spring Boot
 * application properties.</p>
 */

@Getter
@Setter
public class DatabaseProperty {

    /**
     * The JDBC URL of the database.
     * Example: {@code jdbc:mysql://localhost:3306/mydatabase}
     */
    private String url;

    /**
     * The username required to authenticate with the database.
     */
    private String username;

    /**
     * The password associated with the database user.
     * <p><b>Security Notice:</b> Avoid exposing or logging this property.</p>
     */
    private String password;

    /**
     * The fully qualified class name of the JDBC driver.
     * Example: {@code org.postgresql.Driver}, {@code com.mysql.cj.jdbc.Driver}
     */
    private String driverClassName;
}
