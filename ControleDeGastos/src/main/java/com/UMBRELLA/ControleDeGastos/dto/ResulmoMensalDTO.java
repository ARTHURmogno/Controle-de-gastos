package com.UMBRELLA.ControleDeGastos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResulmoMensalDTO {

    private LocalDate ano;
    private LocalDate mes;
    private BigDecimal totalGasto;
    private int quantidadeDeGastos;

}
