package com.UMBRELLA.ControleDeGastos.dto;


import java.time.LocalDate;
import java.math.BigDecimal;

import com.UMBRELLA.ControleDeGastos.model.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor 
public class GastoResponseDTO {

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private Categoria categoria;


}
