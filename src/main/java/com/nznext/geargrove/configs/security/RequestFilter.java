package com.nznext.geargrove.configs.security;

import com.nznext.geargrove.login.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom request filter for handling JWT-based authentication.
 *
 * <p>This filter intercepts incoming HTTP requests, extracts the JWT token from the Authorization
 * header, validates the token, and sets the authentication context for the user if the token is valid.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Extracts the JWT token from the Authorization header (expects the format "Bearer {token}").</li>
 *   <li>Validates the JWT token and extracts the username and roles if valid.</li>
 *   <li>Sets the authentication in the {@link SecurityContextHolder} with the extracted username and roles.</li>
 * </ul>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Slf4j} - Provides logging functionality for the class.</li>
 *   <li>{@code @Component} - Marks this class as a Spring component, automatically registered as a bean.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor for required fields, making the class
 *   dependency-injected and ensuring {@link JwtTokenUtils} is injected.</li>
 * </ul>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {

    /** The utility class for handling JWT token validation and extraction. */
    private final JwtTokenUtils jwtTokenUtils;

    /**
     * Filters incoming requests to extract and validate the JWT token,
     * and sets the authentication context if the token is valid.
     *
     * <p>This method:</p>
     * <ul>
     *   <li>Extracts the JWT token from the "Authorization" header if present.</li>
     *   <li>Validates the token and extracts the username and roles.</li>
     *   <li>Sets the {@link UsernamePasswordAuthenticationToken} in the security context
     *   if the token is valid, allowing the user to be authenticated for further requests.</li>
     * </ul>
     *
     * <p>If the token is invalid or expired, it logs the error and skips authentication.</p>
     *
     * @param request the HTTP request.
     * @param response the HTTP response.
     * @param filterChain the filter chain to pass the request and response if valid.
     * @throws ServletException if an error occurs during filtering.
     * @throws IOException if an error occurs while processing the request or response.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Incoming request: {} {}", request.getMethod(), request.getRequestURL());

        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        // Check if the Authorization header is present and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            log.info("Extracted JWT token: {}", jwtToken);

            try {
                // Validate the token
                if (jwtTokenUtils.isValidToken(jwtToken)) {
                    username = jwtTokenUtils.getUsername(jwtToken);
                    log.info("Valid token for user: {}", username);
                } else {
                    log.warn("Invalid token received");
                }
            } catch (ExpiredJwtException ex) {
                log.error("Expired JWT token: {}", ex.getMessage());
            } catch (SignatureException ex) {
                log.error("Invalid signature in JWT token: {}", ex.getMessage());
            } catch (Exception ex) {
                log.error("Exception while validating JWT token: {}", ex.getMessage());
            }
        } else {
            log.warn("Authorization header is missing or does not start with 'Bearer '");
        }

        // If the username is valid and authentication has not been set, set it in the context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            if (jwtTokenUtils.isValidToken(jwtToken)) {
                @SuppressWarnings("unchecked")
                List<String> roles = jwtTokenUtils.getRolesFromToken(jwtToken);
                log.info("Roles extracted from token: {}", roles);

                authorities.addAll(roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList());
                log.info("Granted authorities: {}", authorities);
            }

            // Set the authentication token in the security context
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, jwtToken, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            log.info("Authentication set for user: {}", username);
        } else {
            log.warn("No valid username found, skipping authentication");
        }
        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
