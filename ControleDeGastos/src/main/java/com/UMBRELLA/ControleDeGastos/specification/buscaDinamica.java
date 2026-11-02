package com.UMBRELLA.ControleDeGastos.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.UMBRELLA.ControleDeGastos.dto.GastoFiltroDTO;
import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;

import com.UMBRELLA.ControleDeGastos.model.Categoria;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.criteria.Predicate;

public class buscaDinamica {

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public static Specification<ModeloDeGasto> filtro(GastoFiltroDTO dto) {
        return (root, query, builder) -> {
            Predicate predicate = builder.conjunction();

            if (dto.getCategoria() != null) {
                Predicate predicateCategoria = builder.equal(root.get("Categoria"), dto.getCategoria());

                predicate = builder.and(predicate, predicateCategoria);
            }

            if (dto.getValor() != null && dto.getValor().compareTo(BigDecimal.ZERO) <= 0) {

                Predicate predicateValor = builder.equal(root.get("Valor"), dto.getValor());

                predicate = builder.and(predicate, predicateValor);
            }

            if (dto.getDataMinima() != null) {
                Predicate predicateData = builder.greaterThanOrEqualTo(root.get("Data"), dto.getDataMinima());

                predicate = builder.and(predicate, predicateData);
            }




            return predicate;
        };
    }
}
