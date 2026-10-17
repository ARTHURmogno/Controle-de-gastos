package com.UMBRELLA.ControleDeGastos.dtoException;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter 
public class ErroResponse {

    private int status;
    private String message;
    private Map<String, List<String>> erros;

    public ErroResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErroResponse(int status, Map<String, List<String>> erros) {
        this.status = status;
        this.erros = erros;
    }



}
