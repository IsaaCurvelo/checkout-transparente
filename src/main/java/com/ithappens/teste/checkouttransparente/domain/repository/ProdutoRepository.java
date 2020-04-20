package com.ithappens.teste.checkouttransparente.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ithappens.teste.checkouttransparente.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, 
										JpaSpecificationExecutor<Produto> {
}
