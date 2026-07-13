package com.hotel.booking.api.security;

import com.hotel.booking.api.domain.auth.service.JWTService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwt;
    private final CustomUserDetailsService custom;

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String AUTH_PATH = "/api/auth/";

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain chain) throws ServletException, IOException {
        String token = extractToken(request);
        if (token != null) authenticateUserIfValid(token);
        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) return null;
        return authHeader.substring(BEARER_PREFIX.length()).trim();
    }

    private void authenticateUserIfValid(String token) {
        String username = jwt.extractUsername(token);
        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) return;
        UserDetails userDetails = custom.loadUserByUsername(username);
        if (jwt.isTokenValid(token, userDetails)) createSecurityContext(userDetails);
    }

    private void createSecurityContext(UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith(AUTH_PATH);
    }

}
