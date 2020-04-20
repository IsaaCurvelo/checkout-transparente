package com.ithappens.teste.checkouttransparente.domain.exception;

public class ProdutoJaIncluidoExceptionException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	public ProdutoJaIncluidoExceptionException(Long produtoId, Long pedidoId) {
		this(String.format("O Produto de id %d já foi incluído no Pedido de id %d", produtoId, pedidoId));
	}
	
	public ProdutoJaIncluidoExceptionException(String mensagem) {
        super(mensagem);
    }
}
