package com.amouamado.amou_amado.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
//import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

//@Component
public class JwtAuthenticationFilter implements Filter {

    private final String SECRET_KEY = "MinhaChaveSecretaJwtSegura1234567890"; // deve ter pelo menos 32 caracteres

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();
        String method = req.getMethod();

        // Ignora o filtro para estas rotas públicas
        if (path.equals("/api/eventos") && method.equals("POST")) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = req.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Token ausente ou inválido");
            return;
        }

        String token = authHeader.replace("Bearer ", "");

        try {
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            request.setAttribute("email", claims.getSubject());
            chain.doFilter(request, response);

        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Token inválido ou expirado");
        }
    }
}
