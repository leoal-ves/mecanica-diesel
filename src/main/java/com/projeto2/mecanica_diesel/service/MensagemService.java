package com.projeto2.mecanica_diesel.service;

import org.springframework.stereotype.Service;

import com.projeto2.mecanica_diesel.repository.MensagemRepository;

@Service
public class MensagemService {
    private final MensagemRepository mensagemRepository;

    public MensagemService(MensagemRepository mensagemRepositor) {
        this.mensagemRepository = mensagemRepositor;
    }

    public String obterMensagem() {
        return mensagemRepository.getMensagem();
    }
}
