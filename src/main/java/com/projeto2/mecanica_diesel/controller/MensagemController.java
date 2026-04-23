package com.projeto2.mecanica_diesel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto2.mecanica_diesel.service.MensagemService;

@RestController
@RequestMapping("/api")
public class MensagemController {
    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @GetMapping("/mensagem")
    public String obterMensagem() {
        return mensagemService.obterMensagem();
    }

}
