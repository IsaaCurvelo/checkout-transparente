package com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado;

public class ClienteNaoEncontradoException extends  EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public ClienteNaoEncontradoException(Long id) {
		this(String.format("Não existe um Cliente com o id igual a %d", id));
	}

	public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
