package com.ithappens.teste.checkouttransparente.domain.exception.pagamento;

import com.ithappens.teste.checkouttransparente.domain.exception.NegocioException;

public class CartaoExpiradoException extends NegocioException {
	
	private static final long serialVersionUID = 1L;

	public CartaoExpiradoException() {
		super("o cartão de crédito informado está expirado");
	}

}
