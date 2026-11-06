package com.UMBRELLA.ControleDeGastos.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import com.UMBRELLA.ControleDeGastos.dtoException.ErroResponse;
import com.UMBRELLA.ControleDeGastos.model.Categoria;

import tools.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice 
public class GlobalExceptionHandler {


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroResponse> tratarErroDeStatus(ResponseStatusException ex) {

        ErroResponse erroResponse = new ErroResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponse);
        }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarMultiplosErros(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getBindingResult().getFieldErrors();

        Map<String, List<String>> errosPorCampo = new HashMap<>();

            for (FieldError erro : erros) {

            String compo = erro.getField();
            String mensagem = erro.getDefaultMessage();

             if ("typeMismatch".equals(erro.getCode())) {

                if (compo.equals("Categoria")) {
                    String enumErro = "Campo: " + compo + ", Deve ser escrito em maiúsculo ou é inexistente.";

                mensagem = enumErro;
                } else {
                    
                    mensagem = "Campo: " + compo + " inválido ou inexistente.";
                }
            }

            errosPorCampo.computeIfAbsent(
                compo,
                chave -> new ArrayList<>()
            ).add(mensagem);
        }

        ErroResponse erroResponse = new ErroResponse(400, errosPorCampo);

        return ResponseEntity.badRequest().body(erroResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> tratarJsonInvalido(HttpMessageNotReadableException ex ) {

        Throwable causa = ex.getCause();

        if (causa instanceof InvalidFormatException erro) {

            if (erro.getTargetType() == Categoria.class) {
                ErroResponse erroCategoria = new ErroResponse(400,"Categoria inválida.");
                return ResponseEntity.badRequest().body(erroCategoria);
            }
        }

        ErroResponse erroResponse = new ErroResponse(400,"As informações enviadas no JSON são inválidas.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResponse> tratarEnumInvalido(MethodArgumentTypeMismatchException ex) {
        ErroResponse erro = new ErroResponse(400, "Categoria Inválido(Categoria deve ser escrito toda em Maiuscula.");

        return ResponseEntity.badRequest().body(erro);
    }
}
