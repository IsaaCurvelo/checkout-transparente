package com.ithappens.teste.checkouttransparente.api.controller;

import static com.ithappens.teste.checkouttransparente.infrastructure.repository.specs.ProdutoSpecs.comDescricaoSemelhante;
import static com.ithappens.teste.checkouttransparente.infrastructure.repository.specs.ProdutoSpecs.comPrecoMaiorQue;
import static com.ithappens.teste.checkouttransparente.infrastructure.repository.specs.ProdutoSpecs.comPrecoMenorQue;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ithappens.teste.checkouttransparente.domain.model.Produto;
import com.ithappens.teste.checkouttransparente.domain.repository.ProdutoRepository;
import com.ithappens.teste.checkouttransparente.domain.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @GetMapping("/{produtoId}")
    public Produto buscar(@PathVariable Long produtoId) {
        return this.produtoService.buscarOuFalhar(produtoId);
    }
    
    @GetMapping
    public List<Produto> listar(String descricao, BigDecimal precoInicial, BigDecimal precoFinal) {
        return this.produtoRepository.findAll(
        		comDescricaoSemelhante(descricao)
        		.and(comPrecoMaiorQue(precoInicial))
        		.and(comPrecoMenorQue(precoFinal))
		);
    }
}
