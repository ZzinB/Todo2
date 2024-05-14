package com.example.todo2.security.filter;

import com.example.todo2.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * OncePerRequestFilter: 하나의 요청에 대해서 한번씩 동작(서블릿 API)
 */
@Log4j2
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if(!path.startsWith("/api/")){
            filterChain.doFilter(request, response);
            return;
        }

        log.info("Token Check Filter .........");
        log.info("JWTUtil: " + jwtUtil);

        filterChain.doFilter(request, response);
    }
}
