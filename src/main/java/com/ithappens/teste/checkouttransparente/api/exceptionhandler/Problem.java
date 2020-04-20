package com.ithappens.teste.checkouttransparente.api.exceptionhandler;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private Integer status;

    private String type;

    private String title;

    private String detail;
    
    private List<String> constraints;

}
