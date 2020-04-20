package com.ithappens.teste.checkouttransparente.domain.exception.pagamento;

import com.ithappens.teste.checkouttransparente.domain.exception.NegocioException;

public class PagamentoRecusadoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public PagamentoRecusadoException() {
		super("a operadora do cartão não aceitou o pagamento do pedido, "
				+ "entre em contato com a operadora ou tente outra forma de pagamento");
	}

}
