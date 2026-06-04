package com.projeto2.mecanica_diesel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LayoutController {
    @GetMapping("/login")
    public String telaLogin() {
        return "login";
    }

    @GetMapping("/perfil")
    public String telaPerfil() {
        return "perfil";
    }

    @GetMapping("/home")
    public String telaHome() {
        return "home";
    }

    @GetMapping("/clientes")
    public String telaClientes() {
        return "clientes";
    }

    @GetMapping("/servicos")
    public String telaServicos() {
        return "servicos";
    }

    @GetMapping("/veiculos")
    public String telaVeiculos() {
        return "veiculos";
    }

    @GetMapping("/clientes/novo")
    public String telaNovoCliente() {
        return "clienteForm";
    }

    @GetMapping()
    public String telaRaiz() {
        return "redirect:/login";
    }

}
