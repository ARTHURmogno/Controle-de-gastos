package com.UMBRELLA.ControleDeGastos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

 
import com.UMBRELLA.ControleDeGastos.model.Categoria;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class GastoFiltroDTO {

    
    @Positive(message = "Valor Máximo deve ser Positivo.")
    private BigDecimal valorMaximo;
    @Positive(message = "Valor Mínimo deve ser Positivo.")
    private BigDecimal valorMinimo;

    private Categoria categoria;

    private LocalDate dataMinima;

    private LocalDate dataMaxima;

}
