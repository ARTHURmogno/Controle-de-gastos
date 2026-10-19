package com.UMBRELLA.ControleDeGastos.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.UMBRELLA.ControleDeGastos.dtoException.ErroResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    /*@ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> tratarErroDeStatus(
            HttpServletRequest request,
            ResponseStatusException ex) {

        HttpStatus status = HttpStatus.valueOf(
                ex.getStatusCode().value()
        );

        Map<String, Object> corpo = Map.of(
                "status", status.value(),
                "mensagem", ex.getReason(),
                "caminho", request.getRequestURI()
        );

        return ResponseEntity.status(status).body(corpo);
    }*/


         @ExceptionHandler(ResponseStatusException.class)
         public ResponseEntity<ErroResponse> tratarErroDeStatus(ResponseStatusException ex) {

                ErroResponse erroResponse = new ErroResponse(404, ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponse);
            }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarMultiplosErros(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getBindingResult().getFieldErrors();

        Map<String, List<String>> errosPorCampo = new HashMap();

        for (FieldError erro : erros) {

            String compo = erro.getField();
            String mensagem = erro.getDefaultMessage();

            List<String> mensagems = errosPorCampo.getOrDefault(mensagem, new ArrayList<>());

            mensagems.add(mensagem);

            errosPorCampo.put(compo, mensagems);
        }

        ErroResponse erroResponse = new ErroResponse(400, errosPorCampo);

        return ResponseEntity.badRequest().body(erroResponse);
    }

}
