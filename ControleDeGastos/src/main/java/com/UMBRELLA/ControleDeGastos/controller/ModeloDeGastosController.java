package com.UMBRELLA.ControleDeGastos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UMBRELLA.ControleDeGastos.service.ModeloDeGastosService;
import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;

@RestController
@RequestMapping("/gastos")
public class ModeloDeGastosController {

    private final ModeloDeGastosService modeloDeGastosService;

    public ModeloDeGastosController(ModeloDeGastosService modeloDeGastosService) {
        this.modeloDeGastosService = modeloDeGastosService;
    }

    @GetMapping
    public List<ModeloDeGasto> mostrarDados() {
        return modeloDeGastosService.mostrarDados();
    }
    
}
