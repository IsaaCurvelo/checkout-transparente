package com.ithappens.teste.checkouttransparente.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ithappens.teste.checkouttransparente.domain.model.Cliente;
import com.ithappens.teste.checkouttransparente.domain.model.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long>, 
											JpaSpecificationExecutor<Pedido> {
	
	public List<Pedido> findByCliente(Cliente cliente);
	
}
