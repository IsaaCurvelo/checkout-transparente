package com.ithappens.teste.checkouttransparente.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pagamento {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JsonBackReference
    private Pedido pedido;
    
    private TipoPagamento tipoPagamento;
    
    @ManyToOne
    private CartaoDeCredito cartaoCredito;

    private Integer quantidadeParcelas;
    
    private LocalDateTime dataAprovacao;
    
    private BigDecimal valor;

}
