package com.UMBRELLA.ControleDeGastos.service;

import java.util.List;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.UMBRELLA.ControleDeGastos.dto.GastoRequestDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoResponseDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoUpdateDTO;
import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;
import com.UMBRELLA.ControleDeGastos.repository.ModeloDeGastosRepository;

@Service
public class ModeloDeGastosService {

    private final ModeloDeGastosRepository modeloDeGastosRepository;

    public ModeloDeGastosService(ModeloDeGastosRepository modeloDeGastosRepository) {
        this.modeloDeGastosRepository = modeloDeGastosRepository;
    }

    public GastoResponseDTO salvarDados(GastoRequestDTO gastoDTO) {

        return modeloDeGastosRepository.save(gastoDTO);

    }

    public List<GastoRequestDTO> mostrarDados() {
        List<ModeloDeGasto> gastos = modeloDeGastosRepository.findAll();

        return gastos;
    }

    public ModeloDeGasto buscarPorId(Long id) {
        return modeloDeGastosRepository.findById(id).orElseThrow
        (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "nada encontrado."));
    }

    public GastoRequestDTO atualizarPorId(GastoUpdateDTO gasto, Long id) {
        ModeloDeGasto novoGasto = buscarPorId(id);

        novoGasto.setDescricao(gasto.getDescricao());
        novoGasto.setValor(gasto.getValor());
        novoGasto.setData(gasto.getData());
        novoGasto.setCategoria(gasto.getCategoria());

        modeloDeGastosRepository.save(novoGasto);

       return novoGasto;
    }

    public GastoResponseDTO deletarPorId(Long id) {
        ModeloDeGasto gasto = buscarPorId(id);

        modeloDeGastosRepository.deleteById(id);
    }

}
