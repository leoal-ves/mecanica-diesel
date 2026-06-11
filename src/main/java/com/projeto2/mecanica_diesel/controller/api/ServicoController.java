package com.projeto2.mecanica_diesel.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto2.mecanica_diesel.repository.ClienteRepository;
import com.projeto2.mecanica_diesel.repository.ServicoRepository;
import com.projeto2.mecanica_diesel.repository.VeiculoRepository;
import com.projeto2.mecanica_diesel.service.AlertaOleoService;
import com.projeto2.mecanica_diesel.dto.ServicoDTO;
import com.projeto2.mecanica_diesel.model.Cliente;
import com.projeto2.mecanica_diesel.model.Servico;
import com.projeto2.mecanica_diesel.model.Veiculo;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoRepository servicoRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;
    private final AlertaOleoService alertaOleoService;

    @PostMapping
    public Servico criarServico(@RequestBody Servico novoServico) {
        
        verificarAlertaQuilometragem(novoServico);
        return servicoRepository.save(novoServico);
    }

    private void verificarAlertaQuilometragem(Servico novoServico) {
        
        if (novoServico.getQuilometragem() == null) {
            return;
        }

        if (novoServico.getDescricao() != null && novoServico.getDescricao().toLowerCase().contains("oleo")) {
            return;
        }

        List<Servico> ultimosOleos = servicoRepository.buscarUltimoServicoPorVeiculoEDescricao(novoServico.getId_veiculo(), "oleo");

        for (Servico oleoAnterior : ultimosOleos) {
            
            if (oleoAnterior.getQuilometragem() == null) {
                continue; 
            }

            System.out.println("Troca de óleo válida para cálculo encontrada! ID: " + oleoAnterior.getId() + " | KM: " + oleoAnterior.getQuilometragem());
            
            if (Boolean.TRUE.equals(oleoAnterior.getAvisoEnviado())) {
                return;
            }

            if (novoServico.getQuilometragem() >= (oleoAnterior.getQuilometragem() + 10000)) {
                
                Cliente cliente = clienteRepository.findById(novoServico.getId_cliente()).orElse(null);
                Veiculo veiculo = veiculoRepository.findById(novoServico.getId_veiculo()).orElse(null);

                if (cliente != null && veiculo != null && cliente.getEmail() != null) {
                    String motivo = "Notamos que o veículo <strong>" + veiculo.getModelo() + "</strong> (Placa: <strong>" + veiculo.getPlaca() + "</strong>) rodou mais de 10.000 KM desde a última troca de óleo registrada conosco.";
                    
                    alertaOleoService.enviarEmail(cliente.getEmail(), cliente.getNome(), veiculo.getPlaca(), veiculo.getModelo(), motivo);
                    
                    oleoAnterior.setAvisoEnviado(true);
                    servicoRepository.save(oleoAnterior);
                }
            }
            return;
        }
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
            s.setQuilometragem(servicoDetails.getQuilometragem());
            return ResponseEntity.ok(servicoRepository.save(s));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarServico(@PathVariable Long id) {
        servicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}