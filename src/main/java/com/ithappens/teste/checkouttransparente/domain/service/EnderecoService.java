package com.ithappens.teste.checkouttransparente.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado.TransportadoraNaoEncontradaException;
import com.ithappens.teste.checkouttransparente.domain.model.Endereco;
import com.ithappens.teste.checkouttransparente.domain.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    

    public Endereco buscarOuFalhar(Long enderecoId) {
        return this.enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new TransportadoraNaoEncontradaException(enderecoId));
    }
    
}
