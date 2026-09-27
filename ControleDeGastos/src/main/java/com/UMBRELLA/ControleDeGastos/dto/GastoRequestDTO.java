package com.UMBRELLA.ControleDeGastos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.UMBRELLA.ControleDeGastos.model.Categoria;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
public class GastoRequestDTO {

    @NotBlank(message = "Descrição é obrigatório")
    @Size(min = 2, max = 500)
    private String descricao;

    @Positive(message = "Valor deve-se positivo")
    @NotNull (message = "O valor é obrigatório")
    private BigDecimal valor;

    @NotNull(message = "A data é obrigatório")
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "A Categoria é obrigatória")
    private Categoria categoria;


}
