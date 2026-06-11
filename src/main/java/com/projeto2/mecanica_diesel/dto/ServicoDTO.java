package com.projeto2.mecanica_diesel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ServicoDTO {
    private Long id;
    private String nomeCliente;
    private String placaVeiculo;
    private String descricao;
    private LocalDate dataServico;
    private Boolean avisoEnviado;
}