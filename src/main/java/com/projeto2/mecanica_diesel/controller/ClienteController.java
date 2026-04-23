package com.projeto2.mecanica_diesel.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projeto2.mecanica_diesel.model.Cliente;
import com.projeto2.mecanica_diesel.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    @GetMapping()
    public List<Cliente> getClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        clienteService.createCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

}
