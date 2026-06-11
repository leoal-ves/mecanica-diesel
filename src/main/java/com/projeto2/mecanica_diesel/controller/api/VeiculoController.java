package com.projeto2.mecanica_diesel.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projeto2.mecanica_diesel.repository.VeiculoRepository;
import com.projeto2.mecanica_diesel.repository.ClienteRepository;
import com.projeto2.mecanica_diesel.model.Veiculo;
import com.projeto2.mecanica_diesel.dto.VeiculoDTO;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    @GetMapping("/cliente/{clienteId}")
    public List<VeiculoDTO> listarVeiculosPorCliente(@PathVariable Long clienteId) {
        return veiculoRepository.findByClienteId(clienteId).stream().map(v -> {
            String nome = clienteRepository.findById(v.getId_cliente())
            .map(c -> c.getNome())
            .orElse("Cliente não encontrado");
            return new VeiculoDTO(v.getId(), v.getModelo(), v.getPlaca(), nome);
        }).collect(java.util.stream.Collectors.toList());
    }

    @PostMapping
    public Veiculo criarVeiculo(@RequestBody Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }
    
    @GetMapping
    public List<VeiculoDTO> listarTodos() {
        return veiculoRepository.findAll().stream().map(v -> {
            String nome = clienteRepository.findById(v.getId_cliente())
                            .map(c -> c.getNome())
                            .orElse("Cliente não encontrado");
            
            return new VeiculoDTO(v.getId(), v.getModelo(), v.getPlaca(), nome);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable Long id) {
        return veiculoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculoDetails) {
        return veiculoRepository.findById(id).map(v -> {
            v.setId_cliente(veiculoDetails.getId_cliente());
            v.setModelo(veiculoDetails.getModelo());
            v.setPlaca(veiculoDetails.getPlaca());
            return ResponseEntity.ok(veiculoRepository.save(v));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVeiculo(@PathVariable Long id) {
        veiculoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}