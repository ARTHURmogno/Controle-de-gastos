package com.UMBRELLA.ControleDeGastos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

 
import com.UMBRELLA.ControleDeGastos.model.Categoria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class FiltroDTO {

    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private Categoria categoria;

}
