package com.projeto2.mecanica_diesel.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto2.mecanica_diesel.model.Usuario;
import com.projeto2.mecanica_diesel.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public String login(Usuario usuario) {
        // Lógica de autenticação aqui
        return "Login bem-sucedido! " + usuario.getEmail() + "senha" + usuario.getSenha();
    }

}
