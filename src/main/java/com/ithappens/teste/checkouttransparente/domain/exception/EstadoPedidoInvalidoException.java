package com.ithappens.teste.checkouttransparente.domain.exception;

import java.util.Arrays;

import com.ithappens.teste.checkouttransparente.domain.model.StatusPedido;

public class EstadoPedidoInvalidoException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	public EstadoPedidoInvalidoException(StatusPedido atual, StatusPedido ...permitidos) {
		this(String.format("operação inválida para o status atual (%s) do pedido. os status permitidos para a operação são: %s", 
				atual.toString(), 
				Arrays.toString(permitidos)
		));
	}
	
	public EstadoPedidoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
