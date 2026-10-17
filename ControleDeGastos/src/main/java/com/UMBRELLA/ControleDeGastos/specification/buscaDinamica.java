package com.UMBRELLA.ControleDeGastos.specification;

import org.springframework.data.jpa.domain.Specification;

import com.UMBRELLA.ControleDeGastos.dto.GastoFiltroDTO;
import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;

import jakarta.persistence.criteria.Predicate;

public class buscaDinamica {

    public static Specification<ModeloDeGasto> filtro(GastoFiltroDTO dto) {
        return (root, query, builder) -> {
            Predicate predicate = builder.conjunction();


            return predicate;
        };
    }
}
