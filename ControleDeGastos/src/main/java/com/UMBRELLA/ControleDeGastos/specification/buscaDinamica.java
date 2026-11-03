package com.UMBRELLA.ControleDeGastos.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.UMBRELLA.ControleDeGastos.dto.GastoFiltroDTO;
import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;

//import com.UMBRELLA.ControleDeGastos.model.Categoria;

import jakarta.persistence.criteria.Predicate;

public class buscaDinamica {

    //private Categoria categoria;

    public static Specification<ModeloDeGasto> comfiltro(GastoFiltroDTO dto) {
        return (root, query, builder) -> {
            Predicate predicate = builder.conjunction();

            if (dto.getCategoria() != null) {
                Predicate predicateCategoria = builder.equal(root.get("categoria"), dto.getCategoria());

                predicate = builder.and(predicate, predicateCategoria);
            }

            if (dto.getValor() != null && dto.getValor().compareTo(BigDecimal.ZERO) <= 0) {

                Predicate predicateValor = builder.equal(root.get("valor"), dto.getValor());

                predicate = builder.and(predicate, predicateValor);
            }

            if (dto.getDataMinima() != null) {
                System.out.println(dto.getDataMinima());
                Predicate predicateDataMinima = builder.greaterThanOrEqualTo(root.get("data"), dto.getDataMinima());

                predicate = builder.and(predicate, predicateDataMinima);
            }

            if (dto.getDataMaxima() != null) {

                Predicate predicateDataMaxima = builder.lessThanOrEqualTo(root.get("data"), dto.getDataMaxima());

                predicate = builder.and(predicate, predicateDataMaxima);
            }

            return predicate;
        };
    }
}
