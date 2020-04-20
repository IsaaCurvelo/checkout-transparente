package com.ithappens.teste.checkouttransparente.api.controller;

import static com.ithappens.teste.checkouttransparente.infrastructure.repository.specs.PedidoSpecs.comClienteId;
import static com.ithappens.teste.checkouttransparente.infrastructure.repository.specs.PedidoSpecs.comStatus;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ithappens.teste.checkouttransparente.domain.exception.NegocioException;
import com.ithappens.teste.checkouttransparente.domain.exception.ProdutoJaIncluidoExceptionException;
import com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado.EntidadeNaoEncontradaException;
import com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado.ProdutoNaoEncontradoException;
import com.ithappens.teste.checkouttransparente.domain.model.Endereco;
import com.ithappens.teste.checkouttransparente.domain.model.ItemPedido;
import com.ithappens.teste.checkouttransparente.domain.model.Pagamento;
import com.ithappens.teste.checkouttransparente.domain.model.Pedido;
import com.ithappens.teste.checkouttransparente.domain.model.StatusPedido;
import com.ithappens.teste.checkouttransparente.domain.model.Transportadora;
import com.ithappens.teste.checkouttransparente.domain.repository.PedidoRepository;
import com.ithappens.teste.checkouttransparente.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
      
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @GetMapping
    public List<Pedido> listar(Long clienteId, StatusPedido statusPedido) {
    	return this.pedidoRepository.findAll(
    			comClienteId(clienteId)
    			.and(comStatus(statusPedido)));
    }
        
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criarPedido(@RequestBody Pedido pedido) {
    	try {
    		return this.pedidoService.criarPedido(pedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    
    @PostMapping("/{pedidoId}/itens")
    public Pedido adicionarItem(@PathVariable Long pedidoId, 
    		@Valid  @RequestBody ItemPedido itemPedido) {
    	try {
    		return this.pedidoService.adicionarItem(pedidoId, itemPedido);
    	} catch (ProdutoNaoEncontradoException e) {
    		throw new NegocioException(e.getMessage());
    	} catch (ProdutoJaIncluidoExceptionException e) {
    		throw new NegocioException(e.getMessage());
    	}
	}
    
    
    @PostMapping("/{pedidoId}/endereco")
    public Pedido selecionarEndereco(@PathVariable Long pedidoId, 
    		@RequestBody Endereco endereco) {
    	try {
    		return this.pedidoService.selecionarEndereco(pedidoId, endereco);
    	} catch (EntidadeNaoEncontradaException e) {
    		throw new NegocioException(e.getMessage());
    	}
	}
    
    
    @PostMapping("/{pedidoId}/transportadora")
    public Pedido selecionarTransportadora(@PathVariable Long pedidoId, 
    		@RequestBody Transportadora transportadora) {
    	try {
    		return this.pedidoService.selecionarTransportadora(pedidoId, transportadora);
    	} catch (EntidadeNaoEncontradaException e) {
    		throw new NegocioException(e.getMessage());
    	}
	}
    
    
    @PostMapping("/{pedidoId}/efetuar-pagamento")
    public Pedido efetuarPagamento(@PathVariable Long pedidoId, 
    		@RequestBody Pagamento pagamento) {
    	try {
    		return this.pedidoService.efetuarPagamento(pedidoId, pagamento);
    	} catch (EntidadeNaoEncontradaException e) {
    		throw new NegocioException(e.getMessage());
    	}
	}
    
        
    
    @GetMapping("/{pedidoId}/itens")
    public List<ItemPedido> buscarItens(@PathVariable Long pedidoId) {
    	return this.pedidoService.buscarOuFalhar(pedidoId).getItens();
	}
    
    
}
