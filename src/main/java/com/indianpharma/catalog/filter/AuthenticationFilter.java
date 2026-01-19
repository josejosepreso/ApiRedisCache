package com.indianpharma.catalog.filter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.indianpharma.catalog.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException
    {
		final String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
			return;
		}
        
        final String token = authHeader.substring(7);

        final Optional<String> email = this.jwtUtil.getEmail(token);

        if (!email.isPresent()) {
            filterChain.doFilter(request, response);
            return;
        }

        final List<GrantedAuthority> authorities = this.jwtUtil.getAuthorities(token);

        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email.get(), null, authorities);

        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        final String path = request.getServletPath();

        return path.startsWith("/api/auth/")
            || path.startsWith("/api/catalog/all");
    }
}
