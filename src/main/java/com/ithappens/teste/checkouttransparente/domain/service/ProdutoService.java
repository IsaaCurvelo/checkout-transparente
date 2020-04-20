package com.ithappens.teste.checkouttransparente.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado.ProdutoNaoEncontradoException;
import com.ithappens.teste.checkouttransparente.domain.model.Produto;
import com.ithappens.teste.checkouttransparente.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto buscarOuFalhar(Long produtoId) {
        return this.produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId)
		);
    }
}
