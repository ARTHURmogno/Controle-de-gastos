package com.UMBRELLA.ControleDeGastos.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.UMBRELLA.ControleDeGastos.dto.GastoFiltroDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoRequestDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoResponseDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoUpdateDTO;
import com.UMBRELLA.ControleDeGastos.mapper.GastoMapper;
import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;
import com.UMBRELLA.ControleDeGastos.repository.ModeloDeGastosRepository;
import com.UMBRELLA.ControleDeGastos.specification.buscaDinamica;

@Service
public class ModeloDeGastosService {

    private final ModeloDeGastosRepository modeloDeGastosRepository;
    private final GastoMapper gastoMapper;

    public ModeloDeGastosService(ModeloDeGastosRepository modeloDeGastosRepository, GastoMapper gastoMapper) {
        this.modeloDeGastosRepository = modeloDeGastosRepository;
        this.gastoMapper = gastoMapper;
    }

    @Transactional
    public GastoResponseDTO salvarGasto(GastoRequestDTO gastoDTO) {
        ModeloDeGasto gasto = gastoMapper.toEntity(gastoDTO);

        ModeloDeGasto novoGasto = modeloDeGastosRepository.save(gasto);

        return gastoMapper.toResponseDTO(novoGasto);
    }

    @Transactional(readOnly = true) 
    public Page<GastoResponseDTO> mostrarGastos(GastoFiltroDTO filtro, Pageable pageable) {
        Specification<ModeloDeGasto> specification = buscaDinamica.comfiltro(filtro);

        Page<ModeloDeGasto> gastos = modeloDeGastosRepository.findAll(specification, pageable);

        return gastos.map(gastoMapper::toResponseDTO);
    }

    @Transactional
    private ModeloDeGasto buscarPorId(Long id) {
        return modeloDeGastosRepository.findById(id).orElseThrow
        (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "nada encontrado."));
    }

    @Transactional(readOnly = true)
    public GastoResponseDTO buscarDadosPorId(Long id) {
        ModeloDeGasto novoDado = buscarPorId(id);

        return gastoMapper.toResponseDTO(novoDado);
    }

    @Transactional 
    public GastoResponseDTO atualizarPorId(GastoUpdateDTO dto, Long id) {
        ModeloDeGasto novoGasto = buscarPorId(id);

        gastoMapper.atualizarEntidade(dto, novoGasto);

        ModeloDeGasto atualizado = modeloDeGastosRepository.save(novoGasto);

        return gastoMapper.toResponseDTO(atualizado);
    }

    @Transactional
    public void deletarPorId(Long id) {
        buscarPorId(id);

        modeloDeGastosRepository.deleteById(id);

    }

}
