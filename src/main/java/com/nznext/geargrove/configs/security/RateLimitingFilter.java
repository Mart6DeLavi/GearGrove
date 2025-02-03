package com.nznext.geargrove.configs.security;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A custom filter for rate limiting requests to the application.
 *
 * <p>This filter limits the number of requests that can be made from a single IP address
 * within a specified time window. If the limit is exceeded, it responds with a "Too Many Requests"
 * status (HTTP 429).</p>
 *
 * <p>The rate limit is applied using the {@link Bucket} library, which provides
 * a token bucket algorithm to manage requests.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Component} - Marks this class as a Spring component to be automatically registered as a bean.</li>
 * </ul>
 */
@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    /** A map storing rate limit buckets by client IP address. */
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    /**
     * This method performs the filtering logic to apply rate limiting to requests.
     *
     * <p>It checks the request's IP address and retrieves or creates a rate limit bucket associated
     * with that IP. If the request is within the allowed rate limit, it proceeds with the filter chain.
     * If the rate limit is exceeded, it responds with an HTTP status code 429 (Too Many Requests) and
     * a message indicating the limit was exceeded.</p>
     *
     * @param request the HTTP request.
     * @param response the HTTP response.
     * @param filterChain the filter chain to pass the request and response if allowed.
     * @throws ServletException if an error occurs during filtering.
     * @throws IOException if an error occurs while processing the request or response.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String clientIpAddress = request.getRemoteAddr();
        Bucket bucket = buckets.computeIfAbsent(clientIpAddress, k -> createNewBucket());

        // Attempt to consumer a token from a bucket.
        if (bucket.tryConsume(1)) {
            // If token consumed successfully, proceed with the request.
            filterChain.doFilter(request, response);
        } else {
            // If rate limit exceeded, respond with 429 Too Many Requests.
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests");
        }

    }

    /**
     * Creates a new rate limit bucket with a refill strategy.
     *
     * <p>This method configures the rate limiting bucket to allow up to 10 requests per minute,
     * with a greedy refill strategy. This means that the bucket will be filled with 10 tokens
     * every minute, allowing up to 10 requests in that time window.</p>
     *
     * @return a new {@link Bucket} configured with the limit and refill strategy.
     */
    private Bucket createNewBucket() {
        // Configure the refill strategy to allow 10 requests every 1 minute.
        Refill refill = Refill.greedy(10, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(10, refill);
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }
}
