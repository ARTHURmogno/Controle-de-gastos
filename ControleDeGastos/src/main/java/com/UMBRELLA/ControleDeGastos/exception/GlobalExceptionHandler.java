package com.UMBRELLA.ControleDeGastos.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> tratarErroDeStatus(
            ResponseStatusException ex) {

        HttpStatus status = HttpStatus.valueOf(
                ex.getStatusCode().value()
        );

        Map<String, Object> corpo = Map.of(
                "status", status.value(),
                "mensagem", ex.getReason(),
                "caminho", "/gastos"
        );

        return ResponseEntity.status(status).body(corpo);
    }

}
