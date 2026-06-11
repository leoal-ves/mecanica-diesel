package com.projeto2.mecanica_diesel.controller.api;

import org.springframework.web.bind.annotation.*;
import com.projeto2.mecanica_diesel.repository.VeiculoRepository;
import com.projeto2.mecanica_diesel.model.Veiculo;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;

    @GetMapping("/cliente/{clienteId}")
    public List<Veiculo> listarVeiculosPorCliente(@PathVariable Long clienteId) {
        return veiculoRepository.findByClienteId(clienteId);
    }
}