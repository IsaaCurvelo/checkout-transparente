package com.ithappens.teste.checkouttransparente.infrastructure.repository.specs;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.ithappens.teste.checkouttransparente.domain.model.Produto;

public class ProdutoSpecs {

	public static Specification<Produto> comDescricaoSemelhante(String descricao) {
		if (!StringUtils.hasText(descricao)) {
			return (root, query, builder) -> builder.isTrue(builder.literal(true));
		}
		return (root, query, builder) ->
			builder.like(root.get("descricao"), "%" + descricao + "%");
	}

	public static Specification<Produto> comPrecoMenorQue(BigDecimal preco) {
		if (preco == null) {
			return (root, query, builder) -> builder.isTrue(builder.literal(true));
		}
		return (root, query, builder) ->
			builder.lessThanOrEqualTo(root.get("preco"), preco);
	}
	
	public static Specification<Produto> comPrecoMaiorQue(BigDecimal preco) {
		if (preco == null) {
			return (root, query, builder) -> builder.isTrue(builder.literal(true));
		}
		return (root, query, builder) ->
			builder.greaterThanOrEqualTo(root.get("preco"), preco);
	}
}
