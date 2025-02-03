package com.nznext.geargrove.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Basic security configuration class for the GearGrove application.
 *
 * <p>This configuration class sets up basic security features including the password encoder and
 * authentication manager necessary for securing the application.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Configuration} - Marks this class as a Spring configuration class.</li>
 *   <li>{@code @EnableWebSecurity} - Enables Spring Security for the application.</li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {

    /**
     * Bean definition for {@link BCryptPasswordEncoder} used for hashing passwords securely.
     *
     * <p>{@code BCryptPasswordEncoder} is a widely used password encoder that uses the BCrypt hashing function.</p>
     *
     * @return a {@link BCryptPasswordEncoder} instance for password hashing.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    /**
     * Bean definition for {@link AuthenticationManager} to manage authentication.
     *
     * <p>This method configures and returns an {@code AuthenticationManager} instance required
     * for authenticating users.</p>
     *
     * @param authenticationConfiguration the Spring Security authentication configuration.
     * @return an {@link AuthenticationManager} instance for authenticating users.
     * @throws Exception if there is an issue with the authentication manager configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
