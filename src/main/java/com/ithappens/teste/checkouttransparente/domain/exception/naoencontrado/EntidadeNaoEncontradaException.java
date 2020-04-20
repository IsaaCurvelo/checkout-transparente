package com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado;

import com.ithappens.teste.checkouttransparente.domain.exception.NegocioException;

public class EntidadeNaoEncontradaException extends  NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
