package com.UMBRELLA.ControleDeGastos.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;
import com.UMBRELLA.ControleDeGastos.repository.ModeloDeGastosRepository;

@Service
public class ModeloDeGastosService {

    private final ModeloDeGastosRepository modeloDeGastosRepository;

    public ModeloDeGastosService(ModeloDeGastosRepository modeloDeGastosRepository) {
        this.modeloDeGastosRepository = modeloDeGastosRepository;
    }

    public ModeloDeGasto salvarDados(ModeloDeGasto gasto) {

        return modeloDeGastosRepository.save(gasto);

    }

    public List<ModeloDeGasto> mostrarDados() {
        List<ModeloDeGasto> gastos = modeloDeGastosRepository.findAll();

        return gastos;
    }

    public ModeloDeGasto buscarPorId(Long id) {
        return modeloDeGastosRepository.findById(id).orElseThrow
        (() -> new IllegalArgumentException("nada encontrado."));
    }

}
