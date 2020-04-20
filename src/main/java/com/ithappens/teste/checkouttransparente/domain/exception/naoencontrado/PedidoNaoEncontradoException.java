package com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado;

public class PedidoNaoEncontradoException extends  EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public PedidoNaoEncontradoException(Long id) {
		this(String.format("Não existe um Pedido com o id igual a %d", id));
	}

	public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
