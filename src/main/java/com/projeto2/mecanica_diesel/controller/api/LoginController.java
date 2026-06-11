package com.projeto2.mecanica_diesel.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projeto2.mecanica_diesel.model.Usuario;
import com.projeto2.mecanica_diesel.service.UsuarioService;
import com.projeto2.mecanica_diesel.service.TokenService;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
    
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioAutenticado = usuarioService.login(usuario);
        
        if (usuarioAutenticado.isPresent()) {
            String token = tokenService.gerarToken(usuarioAutenticado.get());
            
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.badRequest().body(Map.of("erro", "Credenciais inválidas"));
        }
    }
}