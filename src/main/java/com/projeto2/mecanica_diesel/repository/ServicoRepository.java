package com.projeto2.mecanica_diesel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.projeto2.mecanica_diesel.model.Servico;
import com.projeto2.mecanica_diesel.dto.ServicoDTO;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query("SELECT new com.projeto2.mecanica_diesel.dto.ServicoDTO(s.id, c.nome, v.placa, s.descricao, s.dataServico, s.avisoEnviado) " +
           "FROM Servico s " +
           "JOIN Cliente c ON s.id_cliente = c.id " +
           "JOIN Veiculo v ON s.id_veiculo = v.id")
    List<ServicoDTO> findAllComDados();
    List<Servico> findByDescricaoContainingIgnoreCaseAndDataServico(String descricao, LocalDate data);
}
