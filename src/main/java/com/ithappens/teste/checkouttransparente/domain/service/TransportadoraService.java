package com.ithappens.teste.checkouttransparente.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado.TransportadoraNaoEncontradaException;
import com.ithappens.teste.checkouttransparente.domain.model.Transportadora;
import com.ithappens.teste.checkouttransparente.domain.repository.TransportadoraRepository;

@Service
public class TransportadoraService {

    @Autowired
    private TransportadoraRepository transportadoraRepository;
    

    public Transportadora buscarOuFalhar(Long transportadoraId) {
        return this.transportadoraRepository.findById(transportadoraId)
                .orElseThrow(() -> new TransportadoraNaoEncontradaException(transportadoraId));
    }
    
}
