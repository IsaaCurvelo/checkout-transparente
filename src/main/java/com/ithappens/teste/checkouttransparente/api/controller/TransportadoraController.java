package com.ithappens.teste.checkouttransparente.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ithappens.teste.checkouttransparente.domain.model.Transportadora;
import com.ithappens.teste.checkouttransparente.domain.repository.TransportadoraRepository;

@RestController
@RequestMapping("/transportadoras")
public class TransportadoraController {

    @Autowired
    private TransportadoraRepository transportadoraRepository;
    
   
    @GetMapping
    public List<Transportadora> listar() {
        return this.transportadoraRepository.findAll();
    }
}
