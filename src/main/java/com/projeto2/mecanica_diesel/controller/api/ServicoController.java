package com.projeto2.mecanica_diesel.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projeto2.mecanica_diesel.repository.ServicoRepository;
import com.projeto2.mecanica_diesel.dto.ServicoDTO;
import com.projeto2.mecanica_diesel.model.Servico;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoRepository servicoRepository;

    @PostMapping
    public Servico criarServico(@RequestBody Servico servico) {
        return servicoRepository.save(servico);
    }

    @GetMapping
    public List<ServicoDTO> listarServicos() {
        return servicoRepository.findAllComDados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServico(@PathVariable Long id) {
        return servicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable Long id, @RequestBody Servico servicoDetails) {
        return servicoRepository.findById(id).map(s -> {
            s.setId_cliente(servicoDetails.getId_cliente());
            s.setId_veiculo(servicoDetails.getId_veiculo());
            s.setDescricao(servicoDetails.getDescricao());
            s.setDataServico(servicoDetails.getDataServico());
            return ResponseEntity.ok(servicoRepository.save(s));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarServico(@PathVariable Long id) {
        servicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}