package com.projeto2.mecanica_diesel.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto2.mecanica_diesel.service.UsuarioService;

@RestController
@RequestMapping("/api/login")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public String login() {
        // Lógica de autenticação aqui
        return "Login bem-sucedido!";
    }

}
