package com.UMBRELLA.ControleDeGastos.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResumoMensalDTO {
    
    private Integer ano;
    private Integer mes;
    private BigDecimal totalGasto;

}
