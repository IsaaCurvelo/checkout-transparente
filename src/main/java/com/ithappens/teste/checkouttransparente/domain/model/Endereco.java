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
public class Endereco {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Cliente cliente;

    @NotEmpty(message = "o cep é obrigatório")
    private String cep;

    @NotEmpty(message = "o logradouro é obrigatório")
    private String logradouro;

    private String numero;

    private String complemento;

    @NotEmpty(message = "o bairro é obrigatório")
    private String bairro;

    @NotEmpty(message = "a cidade é obrigatório")
    private String cidade;

    @NotEmpty(message = "o estado é obrigatório")
    private String estado;

}
