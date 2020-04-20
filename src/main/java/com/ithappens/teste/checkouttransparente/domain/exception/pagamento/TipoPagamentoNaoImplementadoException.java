package com.ithappens.teste.checkouttransparente.domain.exception.pagamento;

import com.ithappens.teste.checkouttransparente.domain.exception.NegocioException;

public class TipoPagamentoNaoImplementadoException extends NegocioException {
	
	private static final long serialVersionUID = 1L;

	public TipoPagamentoNaoImplementadoException() {
		super("o tipo de pagamento selecionado ainda não está implementado :/");
	}

}
