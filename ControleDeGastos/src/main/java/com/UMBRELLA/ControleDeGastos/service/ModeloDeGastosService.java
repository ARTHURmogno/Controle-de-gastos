package com.UMBRELLA.ControleDeGastos.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.UMBRELLA.ControleDeGastos.dto.GastoFiltroDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoRequestDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoResponseDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoUpdateDTO;
import com.UMBRELLA.ControleDeGastos.dto.ResumoMensalDTO;
import com.UMBRELLA.ControleDeGastos.exception.GastoNaoEncontradoException;
import com.UMBRELLA.ControleDeGastos.exception.ValidacaoQueryException;
import com.UMBRELLA.ControleDeGastos.mapper.GastoMapper;
import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;
import com.UMBRELLA.ControleDeGastos.repository.ModeloDeGastosRepository;
import com.UMBRELLA.ControleDeGastos.repository.ResumoDeGastosRepository;
import com.UMBRELLA.ControleDeGastos.specification.BuscaDinamica;

@Service
public class ModeloDeGastosService {

    private final ModeloDeGastosRepository modeloDeGastosRepository;
    private final GastoMapper gastoMapper;
    private final ResumoDeGastosRepository resumoDeGastosRepository;

    public ModeloDeGastosService(ResumoDeGastosRepository resumoDeGastosRepository, ModeloDeGastosRepository modeloDeGastosRepository, GastoMapper gastoMapper) {
        this.modeloDeGastosRepository = modeloDeGastosRepository;
        this.gastoMapper = gastoMapper;
        this.resumoDeGastosRepository = resumoDeGastosRepository;
    }

    @Transactional
    public GastoResponseDTO salvarGasto(GastoRequestDTO gastoDTO) {
        ModeloDeGasto gasto = gastoMapper.toEntity(gastoDTO);

        ModeloDeGasto novoGasto = modeloDeGastosRepository.save(gasto);

        return gastoMapper.toResponseDTO(novoGasto);
    }

    private void validarFiltro(GastoFiltroDTO filtro) {
         if (filtro.getDataMaxima() != null && filtro.getDataMinima() != null && filtro.getDataMinima().isAfter(filtro.getDataMaxima())) {
            throw new ValidacaoQueryException("Data Máxima deve ser Maior ou igual a Data Mínima.");
        }
        if (filtro.getValorMaximo() != null && filtro.getValorMinimo() != null && filtro.getValorMinimo().compareTo(filtro.getValorMaximo()) >0 ) {
            throw new ValidacaoQueryException("Valor Máximo deve ser maior ou igual a Valor Mínimo.");
        }
    }

    @Transactional(readOnly = true)
    public List<ResumoMensalDTO> buscarResumoMensal(GastoFiltroDTO filtro) {
        validarFiltro(filtro);

        ZoneId fusoManaus = ZoneId.of("America/Manaus");

        YearMonth anoMesAtual = YearMonth.now(fusoManaus);

        GastoFiltroDTO filtroFinal = filtro;

        if (filtro.getDataMinima() == null && filtro.getDataMaxima() == null) {

            LocalDate primeiroDia = anoMesAtual.minusMonths(11).atDay(1);
            LocalDate ultimoDia = anoMesAtual.atEndOfMonth();

            filtroFinal = new GastoFiltroDTO();

            filtroFinal.setCategoria(filtro.getCategoria());
            filtroFinal.setValorMinimo(filtro.getValorMinimo());
            filtroFinal.setValorMaximo(filtro.getValorMaximo());

            filtroFinal.setDataMinima(primeiroDia);
            filtroFinal.setDataMaxima(ultimoDia);
        }


        return resumoDeGastosRepository.resumoMensal(filtroFinal);
    }

    @Transactional(readOnly = true) 
    public Page<GastoResponseDTO> buscarGastos(GastoFiltroDTO filtro, Pageable pageable) {
        validarFiltro(filtro);
        Specification<ModeloDeGasto> specification = BuscaDinamica.comFiltro(filtro);

        Page<ModeloDeGasto> gastos = modeloDeGastosRepository.findAll(specification, pageable);

        return gastos.map(gastoMapper::toResponseDTO);
    }

    private ModeloDeGasto buscarPorId(Long id) {
        return modeloDeGastosRepository.findById(id).orElseThrow
        (() -> new GastoNaoEncontradoException("Gasto com id: '" + id + "' não encontrado."));
    }

    @Transactional(readOnly = true)
    public GastoResponseDTO buscarDadosPorId(Long id) {
        ModeloDeGasto novoDado = buscarPorId(id);

        return gastoMapper.toResponseDTO(novoDado);
    }

    @Transactional 
    public GastoResponseDTO atualizarPorId(GastoUpdateDTO dto, Long id) {
        ModeloDeGasto gastosExistente = buscarPorId(id);

        gastoMapper.atualizarEntidade(dto, gastosExistente);

        ModeloDeGasto atualizado = modeloDeGastosRepository.save(gastosExistente);

        return gastoMapper.toResponseDTO(atualizado);
    }

    @Transactional
    public void deletarPorId(Long id) {

        if (!modeloDeGastosRepository.existsById(id)) {
        throw new GastoNaoEncontradoException("Gasto com id: '" + id + "' não encontrado.");
        }

        modeloDeGastosRepository.deleteById(id);

    }

}