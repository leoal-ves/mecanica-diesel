package com.projeto2.mecanica_diesel.controller.api;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> criarCliente(@RequestBody Cliente cliente) {
        if (clienteService.createCliente(cliente) != null) {
            return ResponseEntity.ok(Map.of("message", "Cliente criado com sucesso"));
        } else {
            return ResponseEntity.status(500).body(Map.of("message", "Erro ao criar cliente"));
        }
    }
    

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.updateCliente(id, cliente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.ok(Map.of("message", "Cliente deletado com sucesso"));
    }

}
