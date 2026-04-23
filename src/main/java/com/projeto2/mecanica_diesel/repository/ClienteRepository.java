package com.projeto2.mecanica_diesel.repository;

import org.springframework.stereotype.Repository;

import com.projeto2.mecanica_diesel.model.Cliente;

@Repository
public interface ClienteRepository extends org.springframework.data.jpa.repository.JpaRepository<Cliente, Long> {

}
