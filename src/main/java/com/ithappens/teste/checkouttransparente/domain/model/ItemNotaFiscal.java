package com.ithappens.teste.checkouttransparente.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemNotaFiscal {

	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JsonBackReference
	private NotaFiscal notaFiscal;
	
	private String descricaoProduto;
	
	private BigDecimal imposto;
	
	private BigDecimal precoUnitario;
	
	private Integer quantidade;
	
	private BigDecimal precoTotal;
}
