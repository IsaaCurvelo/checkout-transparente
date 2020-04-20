package com.ithappens.teste.checkouttransparente.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CartaoDeCredito {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Cliente cliente;
    
    @NotEmpty(message = "o nome do cartão é obrigatório")
    private String nome;
        
    @NotEmpty(message = "a bandeira do cartão é obrigatória")
    private String bandeira;
    
    @NotEmpty(message = "o número do cartão é obrigatório")
    private String numeroCartao;
    
    @NotEmpty(message = "o nome do titular do cartão é obrigatório")
    private String nomeTitularCartao;
    
    @NotEmpty(message = "o código de verificação é obrigatório")
    private String codigoVerificacao;
    
    @NotEmpty(message = "a data de expiração do cartão é obrigatória")
    private String Expiracao;
        
}
