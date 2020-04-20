package com.ithappens.teste.checkouttransparente.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithappens.teste.checkouttransparente.domain.exception.NegocioException;
import com.ithappens.teste.checkouttransparente.domain.model.CartaoDeCredito;
import com.ithappens.teste.checkouttransparente.domain.model.Pagamento;
import com.ithappens.teste.checkouttransparente.domain.model.TipoPagamento;
import com.ithappens.teste.checkouttransparente.domain.repository.CartaoCredidoRepository;
import com.ithappens.teste.checkouttransparente.domain.repository.PagamentoRepository;
import com.ithappens.teste.checkouttransparente.infrastructure.services.pagamento.EfetuadorPagamento;

@Service
@Transactional
public class PagamentoService {

    @Autowired
    private EfetuadorPagamento efetuador;
    
    @Autowired
    private PagamentoRepository pagamentoRepository;
    
    @Autowired
    private CartaoCredidoRepository cartaoCredidoRepository;
    
    public Pagamento efetuarPagamento(Pagamento pagamento) {
    	if (pagamento.getTipoPagamento().equals(TipoPagamento.CREDITO)) {
    		
    		if(pagamento.getCartaoCredito() == null) {
    			throw new NegocioException("quando o pagamento é do tipo crédito, um cartão deverá ser fornecido");
    		}
    		
    		CartaoDeCredito cartao = this.cartaoCredidoRepository
    				.findById(pagamento.getCartaoCredito().getId())
    				.orElseThrow( () -> new NegocioException(
    						"O cartão de crédito informado não foi encontrado")
					);
    		pagamento.setCartaoCredito(cartao);
    	}
    	
    	
    	pagamento = this.efetuador.pagar(pagamento);
    	
    	return this.pagamentoRepository.save(pagamento);
    }
}
