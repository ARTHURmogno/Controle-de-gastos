package com.UMBRELLA.ControleDeGastos.mapper;

import org.springframework.stereotype.Component;

import com.UMBRELLA.ControleDeGastos.dto.GastoRequestDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoResponseDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoUpdateDTO;
import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;

@Component 
public class GastoMapper {

    public ModeloDeGasto toEntity(GastoRequestDTO dto) {
        ModeloDeGasto gasto = new ModeloDeGasto();

        gasto.setDescricao(dto.getDescricao());
        gasto.setValor(dto.getValor());
        gasto.setData(dto.getData());
        gasto.setCategoria(dto.getCategoria());

        return gasto;
    }

    public GastoResponseDTO toResponseDTO(ModeloDeGasto gasto) {
        GastoResponseDTO dto = new GastoResponseDTO();

        dto.setId(gasto.getId());
        dto.setDescricao(gasto.getDescricao());
        dto.setValor(gasto.getValor());
        dto.setData(gasto.getData());
        dto.setCategoria(gasto.getCategoria());

        return dto;
    }

    public void atualizarEntidade(GastoUpdateDTO dto, ModeloDeGasto gasto) {
        if (dto.getDescricao() != null) {
            gasto.setDescricao(dto.getDescricao());
        }
        if (dto.getValor() != null) {
            gasto.setValor(dto.getValor());
        }
        if (dto.getData() != null) {
            gasto.setData(dto.getData());
        }
        if (dto.getCategoria() != null) {
            gasto.setCategoria(dto.getCategoria());
        }
    }

}
