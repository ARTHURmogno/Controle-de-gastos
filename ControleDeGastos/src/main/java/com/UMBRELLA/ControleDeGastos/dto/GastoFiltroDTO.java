package com.UMBRELLA.ControleDeGastos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

 
import com.UMBRELLA.ControleDeGastos.model.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class GastoFiltroDTO {

    private BigDecimal valor;
    private LocalDate data;
    private Categoria categoria;
    
    private LocalDate dataMinima;

    private LocalDate dataMaxima;

}
