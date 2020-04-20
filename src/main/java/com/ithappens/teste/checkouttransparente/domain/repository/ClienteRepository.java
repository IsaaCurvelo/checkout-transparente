package com.ithappens.teste.checkouttransparente.domain.repository;

import com.ithappens.teste.checkouttransparente.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
