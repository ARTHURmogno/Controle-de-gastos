package com.UMBRELLA.ControleDeGastos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.UMBRELLA.ControleDeGastos.model.Categoria;


import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GastoUpdateDTO {

    @Size(message = "Tamanho mínimo do texto 2 carecteres é tamanho máximo 250 caracteres.", min = 2, max = 250)
    private String descricao;

    @Positive(message = "Valor deve ser positivo.")
    private BigDecimal valor;

    @PastOrPresent(message = "Data não pode ser futura.")
    private LocalDate data;
    
    private Categoria categoria;

}
