package com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado;

public class TransportadoraNaoEncontradaException extends  EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public TransportadoraNaoEncontradaException(Long id) {
		this(String.format("Não existe uma Transportadora com o id igual a %d", id));
	}

	public TransportadoraNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
