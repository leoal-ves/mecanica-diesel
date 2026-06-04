package com.projeto2.mecanica_diesel.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.projeto2.mecanica_diesel.model.Usuario;
import com.projeto2.mecanica_diesel.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        if (usuarioService.login(usuario).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/logout")
    public String logout() {
        // Aqui você pode adicionar lógica para invalidar a sessão do usuário, se
        // necessário
        return "redirect:/login"; // Redireciona para a página de login após o logout
    }

}
