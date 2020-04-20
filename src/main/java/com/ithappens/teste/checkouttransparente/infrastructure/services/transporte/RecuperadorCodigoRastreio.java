package com.ithappens.teste.checkouttransparente.infrastructure.services.transporte;

import org.springframework.stereotype.Component;

import com.ithappens.teste.checkouttransparente.domain.model.Pedido;

@Component
public class RecuperadorCodigoRastreio {
	
	public String getNumeroRastreio (Pedido pedido) {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(pedido.getTransportadora().getNome())
				.append(pedido.getTransportadora().getTaxaFrete().toString())
				.append(pedido.getId().toString());
		Integer codigo = builder.toString().hashCode();
		codigo = Math.abs(codigo);// deu negativo XD
		
		return codigo.toString();
	}
}
