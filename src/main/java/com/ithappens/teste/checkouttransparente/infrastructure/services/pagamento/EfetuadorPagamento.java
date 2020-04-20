package com.ithappens.teste.checkouttransparente.infrastructure.services.pagamento;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.ithappens.teste.checkouttransparente.domain.exception.pagamento.CartaoExpiradoException;
import com.ithappens.teste.checkouttransparente.domain.exception.pagamento.PagamentoRecusadoException;
import com.ithappens.teste.checkouttransparente.domain.exception.pagamento.TipoPagamentoNaoImplementadoException;
import com.ithappens.teste.checkouttransparente.domain.model.Pagamento;
import com.ithappens.teste.checkouttransparente.domain.model.TipoPagamento;

@Component
public class EfetuadorPagamento {
	
		
	public Pagamento pagar(Pagamento pagamento) {
		if (pagamento.getTipoPagamento().equals(TipoPagamento.CREDITO)) {

			// resultado = usarServicosDePagamentoDeTerceiros()
			
			
			// simulação randômica de pagamento
			Random random = new Random();
			int p = random.nextInt(15) + 1;
			
			if (p <= 2) { // 13,33% cartao expirado
				throw new CartaoExpiradoException();
			
			} else if (p <= 6) { // 26,66% pagamento recusado
				throw new PagamentoRecusadoException();
				
			} else {
				pagamento.setDataAprovacao(LocalDateTime.now());
				return pagamento;
			}
			
			
		} else {
			throw new TipoPagamentoNaoImplementadoException();
		}
	}
}
