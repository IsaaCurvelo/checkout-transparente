package com.ithappens.teste.checkouttransparente.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithappens.teste.checkouttransparente.domain.model.ItemNotaFiscal;
import com.ithappens.teste.checkouttransparente.domain.model.ItemPedido;
import com.ithappens.teste.checkouttransparente.domain.model.NotaFiscal;
import com.ithappens.teste.checkouttransparente.domain.model.Pedido;
import com.ithappens.teste.checkouttransparente.domain.repository.NotaFiscalRepository;

@Service
@Transactional
public class NotaFiscalService {
	
	@Autowired
	private NotaFiscalRepository notaFiscalRepository;
	
	private BigDecimal fakeAliquotaFixa = new BigDecimal("0.13");
	
	public NotaFiscal gerarNotaFiscal (Pedido pedido) {

		NotaFiscal nota = new NotaFiscal();
		nota.setPedido(pedido);
		nota.setNomeDestinatario(pedido.getCliente().getNome());
		nota.setCpfDestinatario(pedido.getCliente().getCpf());
		
		nota.setEndereco(pedido.getEnderecoEntrega().getLogradouro());
		nota.setMunicípio(pedido.getEnderecoEntrega().getCidade());
		
		nota.setSubtotal(pedido.getSubtotal());
		nota.setTaxaFrete(pedido.getTaxaFrete());
		nota.setValorTotal(pedido.getValorTotal());
		
		List<ItemNotaFiscal> itensNota = pedido.getItens().stream()
				.map(x -> gerarItemNotaFiscal(x, nota))
				.collect(Collectors.toList());
		
		nota.setItens(itensNota);
		
		return this.notaFiscalRepository.save(nota);
	}
	
	
	
	private ItemNotaFiscal gerarItemNotaFiscal(ItemPedido itemPedido, NotaFiscal notaFiscal) {
		ItemNotaFiscal item = new ItemNotaFiscal();
		item.setNotaFiscal(notaFiscal);
		
		item.setImposto(itemPedido.getPrecoTotal().multiply(this.fakeAliquotaFixa));
		
		item.setDescricaoProduto(itemPedido.getProduto().getDescricao());
		item.setPrecoUnitario(itemPedido.getPrecoUnitario());
		item.setQuantidade(itemPedido.getQuantidade());
		item.setPrecoTotal(itemPedido.getPrecoTotal());
		
		return item;
	}
}

