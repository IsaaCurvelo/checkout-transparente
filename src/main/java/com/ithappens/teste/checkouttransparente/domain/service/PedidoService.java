package com.ithappens.teste.checkouttransparente.domain.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithappens.teste.checkouttransparente.domain.exception.EstadoPedidoInvalidoException;
import com.ithappens.teste.checkouttransparente.domain.exception.ProdutoJaIncluidoExceptionException;
import com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado.PedidoNaoEncontradoException;
import com.ithappens.teste.checkouttransparente.domain.model.Cliente;
import com.ithappens.teste.checkouttransparente.domain.model.Endereco;
import com.ithappens.teste.checkouttransparente.domain.model.ItemPedido;
import com.ithappens.teste.checkouttransparente.domain.model.NotaFiscal;
import com.ithappens.teste.checkouttransparente.domain.model.Pagamento;
import com.ithappens.teste.checkouttransparente.domain.model.Pedido;
import com.ithappens.teste.checkouttransparente.domain.model.StatusPedido;
import com.ithappens.teste.checkouttransparente.domain.model.Transportadora;
import com.ithappens.teste.checkouttransparente.domain.repository.PedidoRepository;
import com.ithappens.teste.checkouttransparente.infrastructure.services.transporte.RecuperadorCodigoRastreio;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private EnderecoService enderecoService;
    
    @Autowired
    private TransportadoraService transportadoraService;
    
    @Autowired
    private PagamentoService pagamentoService;
    
    @Autowired
    private NotaFiscalService notaFiscalService;
    
    @Autowired
    private RecuperadorCodigoRastreio recuperadorCodigoRastreio;
    

    
    public Pedido buscarOuFalhar(Long pedidoId) {
    	return this.pedidoRepository.findById(pedidoId)
    			.orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }
    
    public Pedido criarPedido(Pedido pedido) {
    	
    	Pedido.PedidoBuilder pedidoBuilder = createPedidoBuilderWithDefaults();
    			
    	Cliente cliente = pedido.getCliente();
    	
    	if(cliente != null) {
    		cliente = this.clienteService.buscarOuFalhar(cliente.getId());
    		pedidoBuilder.cliente(cliente);
    	}
    	
    	return this.pedidoRepository.save(pedidoBuilder.build());
    }

    public Pedido adicionarItem(Long pedidoId, ItemPedido itemPedido) {
    	
    	Pedido pedido = this.buscarPedidoEChecarStatus(pedidoId, StatusPedido.CRIADO);
    	
    	Long produtoId = itemPedido.getProduto().getId();
    	
    	if (itemRepetido(pedido, itemPedido)) {
    		throw new ProdutoJaIncluidoExceptionException(produtoId, pedido.getId());
    	}
    	
    	itemPedido.setPedido(pedido);
    	pedido.getItens().add(itemPedido);
    	
    	itemPedido.setProduto(
    			this.produtoService.buscarOuFalhar(
    					itemPedido.getProduto().getId())
		);
    	
    	BigDecimal precoProduto = itemPedido.getProduto().getPreco();
    	
    	itemPedido.setPrecoUnitario(precoProduto);
    	itemPedido.setPrecoTotal(
    			precoProduto.multiply(new BigDecimal(itemPedido.getQuantidade()))
		);
    	
    	BigDecimal subtotal = pedido.getSubtotal();
    	
    	pedido.setSubtotal(subtotal.add(itemPedido.getPrecoTotal()));
    	pedido.setValorTotal(pedido.getSubtotal());
    	
    	return this.pedidoRepository.save(pedido);
    }
    
    
    public Pedido selecionarEndereco(Long pedidoId, Endereco endereco) {
    	Pedido pedido = this.buscarPedidoEChecarStatus(pedidoId, 
    			StatusPedido.CRIADO,
    			StatusPedido.ENDERECO_SELECIONADO); 
    	
    	endereco = this.enderecoService.buscarOuFalhar(endereco.getId());
    	
    	pedido.setEnderecoEntrega(endereco);
    	pedido.setStatusPedido(StatusPedido.ENDERECO_SELECIONADO);
    	
    	return this.pedidoRepository.save(pedido);
	}
    
    
    public Pedido selecionarTransportadora (Long pedidoId, Transportadora transportadora) {
    	
    	Pedido pedido = this.buscarPedidoEChecarStatus(pedidoId, 
    			StatusPedido.ENDERECO_SELECIONADO,
    			StatusPedido.TRANSPORTADORA_SELECIONADA);
    	
    	if (pedido.getItens().isEmpty()) {
    		throw new EstadoPedidoInvalidoException(String.format(
    				"O pedido de código %d está vazio, não é possível selecionar uma transportadora", 
    				pedidoId));
    	}
       	
    	transportadora = this.transportadoraService
    			.buscarOuFalhar(transportadora.getId());
    	
    	pedido.setTransportadora(transportadora);
    	pedido.setStatusPedido(StatusPedido.TRANSPORTADORA_SELECIONADA);
    	pedido.setTaxaFrete(transportadora.getTaxaFrete());
    	pedido.setValorTotal(pedido.getSubtotal().add(pedido.getTaxaFrete()));
    	
    	return this.pedidoRepository.save(pedido);
    }
    
    public Pedido efetuarPagamento (Long pedidoId, Pagamento pagamento ) {
    	
    	Pedido pedido = this.buscarPedidoEChecarStatus(pedidoId, 
    			StatusPedido.TRANSPORTADORA_SELECIONADA);
    	
    	pagamento.setValor(pedido.getSubtotal().add(pedido.getTaxaFrete()));
    	pagamento = this.pagamentoService.efetuarPagamento(pagamento);
    	
    	pedido.setValorTotal(pagamento.getValor());
    	pagamento.setPedido(pedido);
    	pedido.setPagamento(pagamento);
    	
    	NotaFiscal notaFiscal = this.notaFiscalService.gerarNotaFiscal(pedido);
    	
    	pedido.setNotaFiscal(notaFiscal);
    	pedido.setStatusPedido(StatusPedido.PAGAMENTO_CONFIRMADO);
    	pedido.setCodigoRastreio(this.recuperadorCodigoRastreio.getNumeroRastreio(pedido));
    	
    	return this.pedidoRepository.save(pedido);
    }
    
    
    private Pedido buscarPedidoEChecarStatus(Long pedidoId, StatusPedido ...permitidos) {
    	
    	Pedido pedido = this.buscarOuFalhar(pedidoId);
    	StatusPedido atual = pedido.getStatusPedido();
    	
    	if (Arrays.stream(permitidos).anyMatch(x -> x.equals(atual))) {
    		return pedido;
    	}
    	throw new EstadoPedidoInvalidoException(atual, permitidos);
    }
    
	private Pedido.PedidoBuilder createPedidoBuilderWithDefaults() {
		Pedido.PedidoBuilder pedidoBuilder = Pedido.builder()
    			.dataHora(LocalDateTime.now())
    			.statusPedido(StatusPedido.CRIADO)
    			.subtotal(BigDecimal.ZERO)
    			.valorTotal(BigDecimal.ZERO)
    			.taxaFrete(BigDecimal.ZERO)
    			.itens(new ArrayList<ItemPedido>());
		return pedidoBuilder;
	}
        
    private boolean itemRepetido(Pedido pedido, ItemPedido itemPedido) {
    	return pedido.getItens().stream()
		.anyMatch(x -> x.getProduto().equals(itemPedido.getProduto()));
    }
    
	
}


