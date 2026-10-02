package com.UMBRELLA.ControleDeGastos.service;

import java.util.List;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.UMBRELLA.ControleDeGastos.dto.GastoRequestDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoResponseDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoUpdateDTO;
import com.UMBRELLA.ControleDeGastos.mapper.GastoMapper;
import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;
import com.UMBRELLA.ControleDeGastos.repository.ModeloDeGastosRepository;

@Service
public class ModeloDeGastosService {

    private final ModeloDeGastosRepository modeloDeGastosRepository;
    private final GastoMapper gastoMapper;

    public ModeloDeGastosService(ModeloDeGastosRepository modeloDeGastosRepository, GastoMapper gastoMapper) {
        this.modeloDeGastosRepository = modeloDeGastosRepository;
        this.gastoMapper = gastoMapper;
    }

    public GastoResponseDTO salvarDados(GastoRequestDTO gastoDTO) {
        ModeloDeGasto gasto = gastoMapper.toEntity(gastoDTO);

        ModeloDeGasto novoGasto = modeloDeGastosRepository.save(gasto);

        return gastoMapper.toResponseDTO(novoGasto);
    }

    public List<GastoResponseDTO> mostrarDados() {
        List<ModeloDeGasto> gastos = modeloDeGastosRepository.findAll();

        return gastos.stream().map(gastoMapper::toResponseDTO).toList();
    }

    private ModeloDeGasto buscarPorId(Long id) {
        return modeloDeGastosRepository.findById(id).orElseThrow
        (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "nada encontrado."));
    }

    public GastoResponseDTO buscarDadosPorId(Long id) {
        ModeloDeGasto novoDado = buscarPorId(id);

        return gastoMapper.toResponseDTO(novoDado);
    }

    public GastoResponseDTO atualizarPorId(GastoUpdateDTO dto, Long id) {
        ModeloDeGasto novoGasto = buscarPorId(id);

        gastoMapper.atualizarEntidade(dto, novoGasto);

        ModeloDeGasto atualizado = modeloDeGastosRepository.save(novoGasto);

        return gastoMapper.toResponseDTO(atualizado);
    }

    public Long deletarPorId(Long id) {
        buscarPorId(id);

        modeloDeGastosRepository.deleteById(id);

        return id;
    }

}
