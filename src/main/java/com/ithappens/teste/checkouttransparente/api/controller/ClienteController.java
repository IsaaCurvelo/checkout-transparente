package com.ithappens.teste.checkouttransparente.api.controller;

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

import com.ithappens.teste.checkouttransparente.domain.model.CartaoDeCredito;
import com.ithappens.teste.checkouttransparente.domain.model.Cliente;
import com.ithappens.teste.checkouttransparente.domain.model.Endereco;
import com.ithappens.teste.checkouttransparente.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/{clienteId}")
    public Cliente buscar(@PathVariable Long clienteId) {
        return this.clienteService.buscarOuFalhar(clienteId);
    }
    
    @GetMapping("/{clienteId}/enderecos")
    public List<Endereco> listarEnderecos(@PathVariable Long clienteId) {
    	Cliente cliente = this.clienteService.buscarOuFalhar(clienteId);
    	return cliente.getEnderecos();    	
    }
    
    @GetMapping("/{clienteId}/cartoesDeCredito")
    public List<CartaoDeCredito> listarCartoesDeCredito(@PathVariable Long clienteId) {
    	Cliente cliente = this.clienteService.buscarOuFalhar(clienteId);
    	return cliente.getCartoesCredito();    	
    }
    
    @PostMapping("/{clienteId}/enderecos")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionarEndereco(@PathVariable Long clienteId,
    		@Valid @RequestBody Endereco endereco) {
		return this.clienteService.adicionarEndereco(clienteId, endereco);
    }
    
    @PostMapping("/{clienteId}/cartoesDeCredito")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionarCartao(@PathVariable Long clienteId,
    		@Valid @RequestBody CartaoDeCredito cartaoCredito) {
		return this.clienteService.adicionarCartaoCredito(clienteId, cartaoCredito);
    }
    
}
