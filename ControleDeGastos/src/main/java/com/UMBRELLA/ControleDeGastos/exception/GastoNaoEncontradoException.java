package com.UMBRELLA.ControleDeGastos.exception;

import org.springframework.http.HttpStatus;

public class GastoNaoEncontradoException extends RuntimeException {

    private final HttpStatus status;

    public GastoNaoEncontradoException(HttpStatus status, String mensagem) {
        super(mensagem);
        this.status = status;
    }

    private HttpStatus getStatus() {
        return status;
    }

}
