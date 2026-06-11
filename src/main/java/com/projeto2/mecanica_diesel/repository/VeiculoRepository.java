package com.projeto2.mecanica_diesel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.projeto2.mecanica_diesel.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
    @Query("SELECT v FROM Veiculo v WHERE v.id_cliente = :clienteId")
    List<Veiculo> findByClienteId(@Param("clienteId") Long clienteId);
}