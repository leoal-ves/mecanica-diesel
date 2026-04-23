package com.projeto2.mecanica_diesel.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.projeto2.mecanica_diesel.model.Usuario;
import com.projeto2.mecanica_diesel.service.UsuarioService;

@Controller
// @RequestMapping("/api")
public class LoginController {
    @Autowired
    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, Model model) {
        Optional<Usuario> usuario = usuarioService.login(email, senha);
        if (usuario.isPresent()) {
            return "redirect:/home"; // Redireciona para a página inicial após o login
        }
        model.addAttribute("error", "Email ou senha inválidos");
        return "login"; // Redireciona de volta para a página de login em caso de falha
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
