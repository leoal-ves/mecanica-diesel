package com.projeto2.mecanica_diesel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginViewController {
    @GetMapping("/login")
    public String login() {
        return "login"; // Redireciona para a página inicial após o login
    }
}
