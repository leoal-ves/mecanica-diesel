package com.projeto2.mecanica_diesel.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.projeto2.mecanica_diesel.model.Cliente;
import com.projeto2.mecanica_diesel.model.Servico;
import com.projeto2.mecanica_diesel.model.Veiculo;
import com.projeto2.mecanica_diesel.repository.ClienteRepository;
import com.projeto2.mecanica_diesel.repository.ServicoRepository;
import com.projeto2.mecanica_diesel.repository.VeiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlertaOleoService {

    private final ServicoRepository servicoRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;
    private final JavaMailSender mailSender;

    // roda todos os dias as 8 da manhã
    @Scheduled(cron = "0 0 8 * * *")
    public void verificarOleoVencido() {
        LocalDate umAnoAtras = LocalDate.now().minusYears(1);
        List<Servico> servicosOleo = servicoRepository.findByDescricaoContainingIgnoreCaseAndDataServico("oleo", umAnoAtras);

        for (Servico servico : servicosOleo) {
            if (Boolean.TRUE.equals(servico.getAvisoEnviado())) {
                continue;
            }

            Cliente cliente = clienteRepository.findById(servico.getId_cliente()).orElse(null);
            Veiculo veiculo = veiculoRepository.findById(servico.getId_veiculo()).orElse(null);
            
            if (cliente != null && cliente.getEmail() != null && veiculo != null) {
                enviarEmail(cliente.getEmail(), cliente.getNome(), veiculo.getPlaca(), veiculo.getModelo());
                
                servico.setAvisoEnviado(true);
                servicoRepository.save(servico);
            }
        }
    }

    private void enviarEmail(String emailDestino, String nomeCliente, String placa, String modelo) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(emailDestino);
        
        mensagem.setSubject("Aviso: Troca de Óleo Vencendo - Veículo " + placa);
        
        mensagem.setText("Olá, " + nomeCliente + "!\n\n"
                + "Já faz 1 ano desde a sua última troca de óleo do veículo " + modelo + " (Placa: " + placa + ") conosco. "
                + "Para manter o motor funcionando perfeitamente, passe na Cireve Mecânica Diesel para uma revisão.\n\n"
                + "Entre em contato pelo WhatsApp pelo número (41) 99695-9501.\n\n" 
                + "Endereço: Rodovia BR-277, KM 120, S/N - Jardim Bela Vista.");

        mailSender.send(mensagem);
        System.out.println("E-mail enviado para: " + emailDestino + " | Veículo: " + placa);
    }
}