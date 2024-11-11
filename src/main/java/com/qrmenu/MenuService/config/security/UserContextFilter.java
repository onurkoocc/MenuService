package com.qrmenu.MenuService.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

import java.util.Collections;
import java.util.List;
@Component
public class UserContextFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public UserContextFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");
            System.out.println("Received Authorization header: " + authHeader);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {

                String token = authHeader.substring(7);

                if (jwtTokenProvider.validateToken(token)) {

                    String username = jwtTokenProvider.getUsernameFromToken(token);
                    String role = jwtTokenProvider.getUserRoleFromToken(token);

                    // Prefix the role with "ROLE_" if not already prefixed
                    String roleWithPrefix = role.startsWith("ROLE_") ? role : "ROLE_" + role;

                    // Create authorities list
                    List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                            new SimpleGrantedAuthority(roleWithPrefix)
                    );

                    // Create Authentication object
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);

                    // Set the Authentication in the SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            // Log the exception and proceed without setting authentication
            // Alternatively, you can send an error response
            logger.error("Could not set user authentication in security context", e);
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}