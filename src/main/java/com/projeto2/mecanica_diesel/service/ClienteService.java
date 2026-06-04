package com.projeto2.mecanica_diesel.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.projeto2.mecanica_diesel.model.Cliente;
import com.projeto2.mecanica_diesel.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> updateCliente(Long id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setNome(clienteDetails.getNome());
            cliente.setEmail(clienteDetails.getEmail());
            return Optional.of(clienteRepository.save(cliente));
        }
        return Optional.empty();
    }

    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }

    public List<Cliente> searchClientesByName(String nome) {
        // return clienteRepository.findByNomeContainingIgnoreCase(nome);
        return null;
    }
}
