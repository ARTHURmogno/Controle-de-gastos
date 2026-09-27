package com.UMBRELLA.ControleDeGastos.mapper;

import org.springframework.stereotype.Component;

import com.UMBRELLA.ControleDeGastos.dto.GastoRequestDTO;
import com.UMBRELLA.ControleDeGastos.dto.GastoResponseDTO;
import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;

@Component 
public class GastoMapper {

    public static ModeloDeGasto toEntity(GastoRequestDTO dto) {
        ModeloDeGasto gasto = new ModeloDeGasto();

        gasto.setDescricao(dto.getDescricao());
        gasto.setValor(dto.getValor());
        gasto.setData(dto.getData());
        gasto.setCategoria(dto.getCategoria());

        return gasto;
    }

    public static GastoResponseDTO toResponseDTO(ModeloDeGasto gasto) {
        GastoResponseDTO dto = new GastoResponseDTO();

        dto.setDescricao(gasto.getDescricao());
        dto.setValor(gasto.getValor());
        dto.setData(gasto.getData());
        dto.setCategoria(gasto.getCategoria());

        return dto;
    }

}
