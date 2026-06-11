package com.projeto2.mecanica_diesel.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

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
                String motivo = "Já faz 1 ano desde a sua última troca de óleo do veículo <strong>" + veiculo.getModelo() + "</strong> (Placa: <strong>" + veiculo.getPlaca() + "</strong>) conosco.";
                enviarEmail(cliente.getEmail(), cliente.getNome(), veiculo.getPlaca(), veiculo.getModelo(), motivo);
                
                servico.setAvisoEnviado(true);
                servicoRepository.save(servico);
            }
        }
    }

    public void enviarEmail(String emailDestino, String nomeCliente, String placa, String modelo, String motivo) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            
            helper.setTo(emailDestino);
            helper.setSubject("Aviso: Troca de Óleo Vencendo - Veículo " + placa);
            
            String linkWhatsApp = "https://wa.me/5541996959501";
            String linkMaps = "https://www.google.com/maps/search/?api=1&query=Rodovia+BR-277,+KM+120,+Jardim+Bela+Vista";

            String htmlMsg = "<p>Olá, <strong>" + nomeCliente + "</strong>!</p>"
                    + "<p>" + motivo + " Para manter o motor funcionando perfeitamente, passe na Cireve Mecânica Diesel para uma revisão.</p>"
                    + "<p>Entre em contato clicando nos links abaixo:</p>"
                    + "<p>📱 <strong>WhatsApp:</strong> <a href='" + linkWhatsApp + "' target='_blank'>Conversar com a mecânica (41 99695-9501)</a></p>"
                    + "<p>📍 <strong>Endereço:</strong> <a href='" + linkMaps + "' target='_blank'>Abrir no Google Maps</a><br>"
                    + "Rodovia BR-277, KM 120, S/N - Jardim Bela Vista.</p>";

            helper.setText(htmlMsg, true);

            mailSender.send(mimeMessage);
            System.out.println("E-mail enviado para: " + emailDestino + " | Veículo: " + placa);
            
        } catch (MessagingException e) {
            System.err.println("Falha ao enviar e-mail HTML para " + emailDestino);
            e.printStackTrace();
        }
    }
}