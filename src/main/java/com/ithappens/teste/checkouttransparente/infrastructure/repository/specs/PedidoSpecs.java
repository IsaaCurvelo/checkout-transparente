package com.ithappens.teste.checkouttransparente.infrastructure.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import com.ithappens.teste.checkouttransparente.domain.model.Pedido;
import com.ithappens.teste.checkouttransparente.domain.model.StatusPedido;

public class PedidoSpecs {

	public static Specification<Pedido> comClienteId(Long clienteId) {
		if (clienteId == null) {
			return (root, query, builder) -> builder.isTrue(builder.literal(true));
		}
		return (root, query, builder) ->
			builder.equal(root.get("cliente").get("id"), clienteId);
	}
	
	public static Specification<Pedido> comStatus(StatusPedido statusPedido) {
		if (statusPedido == null) {
			return (root, query, builder) -> builder.isTrue(builder.literal(true));
		}
		return (root, query, builder) ->
			builder.equal(root.get("statusPedido"), statusPedido);
	}

}
