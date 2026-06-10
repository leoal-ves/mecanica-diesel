package com.projeto2.mecanica_diesel.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto2.mecanica_diesel.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByDescricaoContainingIgnoreCaseAndDataServico(String descricao, LocalDate data);
}
