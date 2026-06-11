package com.projeto2.mecanica_diesel.config;

import com.projeto2.mecanica_diesel.service.TokenService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authorizationHeader = httpRequest.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            String emailUsuario = tokenService.validarToken(token);

            if (emailUsuario != null) {
                httpRequest.setAttribute("emailUsuario", emailUsuario);
                
                chain.doFilter(request, response);
                return;
            }
        }

        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpResponse.setContentType("application/json");
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.getWriter().write("{\"erro\": \"Acesso negado. Token inválido ou ausente.\"}");
    }
}