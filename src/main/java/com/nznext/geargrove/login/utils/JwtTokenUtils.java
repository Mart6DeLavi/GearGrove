package com.nznext.geargrove.login.utils;

import com.nznext.geargrove.login.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for handling JWT (JSON Web Token) generation and validation in the GearGrove application.
 *
 * <p>This class provides methods to generate JWT tokens for users, extract claims from tokens,
 * and validate the tokens' authenticity. It is used for managing authentication and authorization.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Component} - Marks this class as a Spring component, making it eligible for dependency injection.</li>
 *   <li>{@code @Getter} - Automatically generates getter methods for all fields in the class.</li>
 *   <li>{@code @Slf4j} - Provides logging functionality for the class.</li>
 * </ul>
 */
@Component
@Getter
@Slf4j
public class JwtTokenUtils {

    /** Secret key used for signing JWT tokens. */
    @Value("${jwt.user_secret}")
    private String userSecret;

    /** The lifetime duration of the JWT token. */
    @Value("${jwt.user_secret_lifetime}")
    private Duration userSecretLifetime;

    /**
     * Generates a JWT token for a user based on their details and roles.
     *
     * <p>This method creates a JWT token that includes the user's roles and username as claims.
     * It uses the configured secret key and expiration time to sign and generate the token.</p>
     *
     * @param userDetails the details of the user for whom the token is being generated.
     * @param roles the roles assigned to the user, included in the token as claims.
     * @return the generated JWT token as a {@link String}.
     */
    public String generateUserToken(UserDetails userDetails, Collection<Role> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList()));

        Date issuedDate = new Date();
        Date expirationDate = new Date(issuedDate.getTime() + getUserSecretLifetime().toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, getUserSecret())
                .compact();
    }

    /**
     * Extracts all claims from a JWT token.
     *
     * <p>This method parses the JWT token and retrieves the claims stored in it.</p>
     *
     * @param token the JWT token to extract claims from.
     * @return the claims of the token as a {@link Claims} object.
     */
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getUserSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Validates if a JWT token is valid.
     *
     * <p>This method checks if the token is valid by parsing its claims. If parsing is successful,
     * the token is considered valid. If an exception occurs, the token is invalid.</p>
     *
     * @param token the JWT token to validate.
     * @return {@code true} if the token is valid, {@code false} otherwise.
     */
    public boolean isValidToken(String token) {
        try {
            getAllClaimsFromToken(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Extracts the username from a JWT token.
     *
     * <p>This method retrieves the username from the claims of the given JWT token.</p>
     *
     * @param token the JWT token to extract the username from.
     * @return the username from the token.
     */
    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    /**
     * Extracts the roles from a JWT token.
     *
     * <p>This method retrieves the roles from the claims of the given JWT token.</p>
     *
     * @param token the JWT token to extract roles from.
     * @return a {@link List} of roles extracted from the token.
     */
    public List getRolesFromToken(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }
}