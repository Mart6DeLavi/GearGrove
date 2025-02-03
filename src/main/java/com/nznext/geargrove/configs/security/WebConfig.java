package com.nznext.geargrove.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration class for setting up global web configurations in the GearGrove application.
 *
 * <p>This configuration class handles web-related settings such as Cross-Origin Resource Sharing (CORS)
 * to control how requests from different origins are handled by the backend.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Configures CORS for specific endpoints.</li>
 *   <li>Allows specific origins to access certain endpoints, helping manage cross-origin requests.</li>
 * </ul>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Configuration} - Marks this class as a Spring configuration class.</li>
 * </ul>
 */
@Configuration
public class WebConfig {

    /**
     * Configures CORS (Cross-Origin Resource Sharing) settings for the application.
     *
     * <p>This method creates a custom configuration for CORS to allow requests from
     * a specific origin to access the {@code /api/contact} endpoint.</p>
     *
     * <p>In this case, requests coming from {@code http://localhost:3000} will be allowed.</p>
     *
     * @return a {@link WebMvcConfigurer} instance with custom CORS configuration.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Configures CORS mappings for the application.
             *
             * <p>Only requests to {@code /api/contact} from {@code http://localhost:3000} will be allowed.
             * This is useful for enabling a frontend on one domain to interact with a backend API hosted on another domain.</p>
             *
             * @param registry the CORS registry to configure.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/contact").allowedOrigins("http://localhost:3000"); // Allow access from this origin only
            }
        };
    }
}
