package com.ithappens.teste.checkouttransparente.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Pedido pedido;

    @ManyToOne
    @NotNull(message = "o produto do item é obrigatório")
    private Produto produto;

    @NotNull(message = "a quantidade do item é obrigatória")
    @Min(value = 1, message = "é necessária pelo menos uma unidade do item para colocar no carrinho")
    private Integer quantidade;
    
	private BigDecimal precoUnitario;
	
	private BigDecimal precoTotal;

}
