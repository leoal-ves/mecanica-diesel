package com.projeto2.mecanica_diesel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class VeiculoDTO {
    private Long id;
    private String modelo;
    private String placa;
    private String nomeCliente;
}