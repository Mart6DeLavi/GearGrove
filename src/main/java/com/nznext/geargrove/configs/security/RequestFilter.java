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

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Логируем информацию о входящем запросе
        log.info("Incoming request: {} {}", request.getMethod(), request.getRequestURL());

        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            log.info("Extracted JWT token: {}", jwtToken);  // Логируем токен (не рекомендуется логировать сам токен в продуктиве, лучше логировать, что он есть)

            try {
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

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, jwtToken, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            log.info("Authentication set for user: {}", username);
        } else {
            log.warn("No valid username found, skipping authentication");
        }

        // Пропускаем фильтр дальше
        filterChain.doFilter(request, response);
    }
}
