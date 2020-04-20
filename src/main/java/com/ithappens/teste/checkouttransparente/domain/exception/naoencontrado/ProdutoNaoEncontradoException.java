package com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado;

public class ProdutoNaoEncontradoException extends  EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public ProdutoNaoEncontradoException(Long id) {
		this(String.format("Não existe um Produto com o id igual a %d", id));
	}

	public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
