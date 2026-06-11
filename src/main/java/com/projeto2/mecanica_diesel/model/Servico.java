package com.projeto2.mecanica_diesel.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "servicos")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_cliente;
    private Long id_veiculo;
    private String descricao;
    private LocalDate dataServico;
    private Boolean avisoEnviado;
    private Integer quilometragem;
}
