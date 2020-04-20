package com.ithappens.teste.checkouttransparente.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ithappens.teste.checkouttransparente.domain.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
