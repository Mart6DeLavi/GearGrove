package com.nznext.geargrove.configs.security;

import com.nznext.geargrove.login.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Main security configuration class for the GearGrove application.
 *
 * <p>This configuration sets up authentication providers, security filters, and HTTP security settings
 * for the entire application. It includes:</p>
 * <ul>
 *   <li>Custom authentication provider using {@link DaoAuthenticationProvider}.</li>
 *   <li>Integration with custom security filters for rate limiting and JWT token validation.</li>
 *   <li>Configuration of HTTP security settings including request authorization and CSRF protection.</li>
 * </ul>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Configuration} - Marks this class as a Spring configuration class.</li>
 *   <li>{@code @EnableWebSecurity} - Enables Spring Security for the application.</li>
 *   <li>{@code @AllArgsConstructor} - Automatically generates a constructor for all fields, ensuring proper
 *   dependency injection of the required services and filters.</li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    /** The user service to handle user authentication. */
    private UserService userService;

    /** The custom filter for validating JWT tokens. */
    private RequestFilter requestFilter;

    /** The basic security configuration including password encoding. */
    private BasicSecurityConfig basicSecurityConfig;

    /** The custom filter for rate limiting incoming requests. */
    private RateLimitingFilter rateLimitingFilter;

    /**
     * Sets the user service.
     *
     * @param userService the user service to be injected.
     */
    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Sets the request filter for JWT token validation.
     *
     * @param requestFilter the filter for JWT token validation to be injected.
     */
    @Autowired
    private void setRequestFilter(RequestFilter requestFilter) {
        this.requestFilter = requestFilter;
    }

    /**
     * Sets the basic security configuration.
     *
     * @param basicSecurityConfig the security configuration for basic settings.
     */
    @Autowired
    private void setBasicSecurityConfig(BasicSecurityConfig basicSecurityConfig) {
        this.basicSecurityConfig = basicSecurityConfig;
    }

    /**
     * Defines a {@link DaoAuthenticationProvider} bean for authentication.
     *
     * <p>This provider uses {@link UserService} to load user details
     * to validate passwords for authentication.</p>
     *
     * @return a configured {@link DaoAuthenticationProvider} instance.
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(basicSecurityConfig.passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * Configures the {@link SecurityFilterChain} for HTTP security settings.
     *
     * <p>This method configures CSRF, CORS settings, and request authorization rules,
     * sets up HTTP basic authentication, and adds custom security filters such as the
     * {@link RequestFilter} for JWT validation and the {@link RateLimitingFilter} for rate limiting.</p>
     *
     * @param http the {@link HttpSecurity} object to configure security for HTTP requests.
     * @return a configured {@link SecurityFilterChain} instance.
     * @throws Exception if there is an error configuring HTTP security.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for simplicity (may need re-enabling for specific use cases)
                .cors(AbstractHttpConfigurer::disable) // Disable CORS (Cross-Origin Resource Sharing)
                .authorizeHttpRequests(authorize -> authorize   
                        .anyRequest().permitAll()  // Allow all requests for now (customize for more specific rules)
                )
                .httpBasic(Customizer.withDefaults()) // Enable HTTP basic authentication
                .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class) // Add custom JWT filter before standard authentication filter
                .addFilterBefore(rateLimitingFilter, RequestFilter.class); // Add rate limiting filter before JWT filter
        return http.build();
    }
}
