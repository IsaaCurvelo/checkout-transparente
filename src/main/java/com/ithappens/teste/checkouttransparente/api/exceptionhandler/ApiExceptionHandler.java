package com.ithappens.teste.checkouttransparente.api.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ithappens.teste.checkouttransparente.domain.exception.NegocioException;
import com.ithappens.teste.checkouttransparente.domain.exception.naoencontrado.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
                                                                  WebRequest webRequest) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;

        Problem problem = this.createProblemBuilder(status, problemType, e.getMessage())
                .build();

        return this.handleExceptionInternal(e, problem, new HttpHeaders(), status, webRequest);
    }
    
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException e,
                                                  	WebRequest webRequest) {
    	
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;

        Problem problem = this.createProblemBuilder(status, problemType, e.getMessage())
                .build();

        return this.handleExceptionInternal(e, problem, new HttpHeaders(), status, webRequest);
    }
    
    
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
    		MethodArgumentNotValidException ex,
    		HttpHeaders headers, HttpStatus status, WebRequest request) {

    	status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.CONSTRAINT_VIOLADA;
        
        List<String> constraints = ex.getBindingResult().getFieldErrors()
        		.stream()
        		.map(x -> x.getDefaultMessage())
        		.collect(Collectors.toList());
        
        
        Problem problem = this.createProblemBuilder(status, problemType, ex.getMessage())
        		.constraints(constraints)
        		.detail(null)
                .build();

        return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
    
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, 
                                                             HttpStatus status,
                                                             WebRequest request) {
        if (body == null) {
            body = Problem.builder()
                    .status(status.value())
                    .title(status.getReasonPhrase())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
                                                       ProblemType problemType,
                                                       String detail) {
        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

}
