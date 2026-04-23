package com.projeto2.mecanica_diesel.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MensagemRepository {
    public String getMensagem() {
        return "Bem-vindo à Mecânica Diesel!";
    }
}
