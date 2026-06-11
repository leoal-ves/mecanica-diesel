package com.projeto2.mecanica_diesel.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.projeto2.mecanica_diesel.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    
    private final String secret = "minha_chave_super_secreta_mecanica_diesel"; 

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("mecanica_diesel_api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(dataExpiracao()) 
                    .sign(algorithm);
        } catch (Exception exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("mecanica_diesel_api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception exception) {
            return null; 
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
    }
}