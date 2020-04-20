package com.ithappens.teste.checkouttransparente.domain.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado.ClienteNaoEncontradoException;
import com.ithappens.teste.checkouttransparente.domain.model.CartaoDeCredito;
import com.ithappens.teste.checkouttransparente.domain.model.Cliente;
import com.ithappens.teste.checkouttransparente.domain.model.Endereco;
import com.ithappens.teste.checkouttransparente.domain.repository.ClienteRepository;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    

    public Cliente buscarOuFalhar(Long clienteId) {
        return this.clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
    }
    
    public Cliente adicionarEndereco(Long clienteId, Endereco endereco) {
    	
    	Cliente cliente = this.buscarOuFalhar(clienteId);
    	
    	endereco.setCliente(cliente);
    	cliente.getEnderecos().add(endereco);
    	this.clienteRepository.save(cliente);
    	
    	return cliente;
    }

	public Cliente adicionarCartaoCredito(Long clienteId, @Valid CartaoDeCredito cartaoCredito) {
		Cliente cliente = this.buscarOuFalhar(clienteId);
    	
    	cartaoCredito.setCliente(cliente);
    	cliente.getCartoesCredito().add(cartaoCredito);
    	this.clienteRepository.save(cliente);
    	
    	return cliente;
	}
}
