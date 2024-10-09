package com.logitrack.geargrove.configs.security;

import com.logitrack.geargrove.login.utils.JwtTokenUtils;
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
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            try {
                if (jwtTokenUtils.isValidToken(jwtToken)) {
                    username = jwtTokenUtils.getUsername(jwtToken);
                }
            } catch (ExpiredJwtException | SignatureException ex) {
                log.error(ex.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            if (jwtTokenUtils.isValidToken(jwtToken)) {

                @SuppressWarnings("unchecked")
                List<String> roles = (List<String>) jwtTokenUtils.getRolesFromToken(jwtToken);

                authorities.addAll(roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList());
            }
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, jwtToken, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}
