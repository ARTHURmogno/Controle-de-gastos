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

    @Size(min = 2, max = 100)
    private String descricao;

    @Positive(message = "Valor deve-se positivo")
    private BigDecimal valor;

    @PastOrPresent(message = "Data é abrigatória")
    private LocalDate data;
    
    private Categoria categoria;

}
