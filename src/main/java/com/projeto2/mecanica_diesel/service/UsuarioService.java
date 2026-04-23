package com.projeto2.mecanica_diesel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto2.mecanica_diesel.model.Usuario;
import com.projeto2.mecanica_diesel.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Optional<Usuario> login(String email, String senha) {
        return repository.findByEmail(email)
                .filter(u -> u.getSenha().equals(senha));
    }

}
