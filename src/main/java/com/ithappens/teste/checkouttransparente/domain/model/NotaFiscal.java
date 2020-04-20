package com.ithappens.teste.checkouttransparente.domain.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NotaFiscal {
	
	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToOne
	@JsonBackReference
	private Pedido pedido;
	
	private String nomeDestinatario;
	
	private String cpfDestinatario;
	
	private String endereco;
	
	private String município;
	
	private BigDecimal subtotal;
	
	private BigDecimal taxaFrete;
	
	private BigDecimal valorTotal;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
	private List<ItemNotaFiscal> itens;
	
	
}
