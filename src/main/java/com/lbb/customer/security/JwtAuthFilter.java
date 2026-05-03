package com.lbb.customer.security;


import com.lbb.customer.security.exception.InvalidTokenException;
import com.lbb.customer.security.exception.TokenExpiredException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {

            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && !authHeader.isBlank()) {
                String token;
                if (authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7);
                } else {
                    token = authHeader;  // รับ token แบบดิบๆ
                }


                String username = jwtService.validate(token);
                Claims claims = jwtService.getClaims(token);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.emptyList()
                        );

                authentication.setDetails(claims);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (TokenExpiredException ex) {
            writeError(response, "ER_INVALID_TOKEN", "missing or invalid token");

        } catch (InvalidTokenException ex) {
            writeError(response, "ER_INVALID_TOKEN", "missing or invalid token");

        } catch (Exception ex) {
            writeError(response, "ER_INVALID_TOKEN", "missing or invalid token");
        }
    }

    private void writeError(HttpServletResponse response,
                            String code,
                            String message) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        response.setContentType("application/json");

        response.getWriter().write("""
        {
          "status": "error",
          "error": {
            "code": "%s"
          },
          "message": "%s"
        }
    """.formatted(code, message));
    }
}

