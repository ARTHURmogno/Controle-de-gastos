package com.UMBRELLA.ControleDeGastos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UMBRELLA.ControleDeGastos.dto.GastoRequestDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoResponseDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoUpdateDTO;
import com.UMBRELLA.ControleDeGastos.service.ModeloDeGastosService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/gastos")
public class ModeloDeGastosController {

    private final ModeloDeGastosService modeloDeGastosService;

    public ModeloDeGastosController(ModeloDeGastosService modeloDeGastosService) {
        this.modeloDeGastosService = modeloDeGastosService;
    }

    @GetMapping
    public ResponseEntity<List<GastoResponseDTO>> mostrarDados() {

        return ResponseEntity.ok(modeloDeGastosService.mostrarDados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GastoResponseDTO> gastosPorId(@PathVariable Long id) {

        return ResponseEntity.ok(modeloDeGastosService.buscarDadosPorId(id));
    }

    @PostMapping 
    public ResponseEntity<GastoResponseDTO> salvarGasto(@RequestBody @Valid GastoRequestDTO gastoDTO) {

        return ResponseEntity.ok(modeloDeGastosService.salvarDados(gastoDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GastoResponseDTO> atualizarGastoPorId(@RequestBody GastoUpdateDTO gasto, @PathVariable Long id) {

        return ResponseEntity.ok(modeloDeGastosService.atualizarPorId(gasto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletarGastoPorId(@PathVariable Long id) {

        return ResponseEntity.ok(modeloDeGastosService.deletarPorId(id));
    }

    }
    
