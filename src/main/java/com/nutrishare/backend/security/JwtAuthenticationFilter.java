package com.nutrishare.backend.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nutrishare.backend.model.User;
import com.nutrishare.backend.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {

        if (isFreePath(request)) {
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            String userId = jwtService.getClaims(token).getSubject();
            User user = userRepository.findById(userId).orElse(null);

            if (user == null) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, List.of());

            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isFreePath(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.equals("/auth/login") || path.equals("/users/register");
    }
}
