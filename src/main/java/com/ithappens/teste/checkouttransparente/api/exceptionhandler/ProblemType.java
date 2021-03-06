package com.ithappens.teste.checkouttransparente.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    CONSTRAINT_VIOLADA("constraint-violada", "Violação de constraint de entidade"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://checkouttransparente.com" + path;
        this.title = title;
    }
}
