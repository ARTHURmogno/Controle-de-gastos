package com.UMBRELLA.ControleDeGastos.specification;

import org.springframework.data.jpa.domain.Specification;

import com.UMBRELLA.ControleDeGastos.dto.GastoFiltroDTO;
import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BuscaDinamica {

     public static Specification<ModeloDeGasto> comFiltro(GastoFiltroDTO dto) {
       return (root, query, builder) -> montarPredicate(root, builder, dto);
    }

    public static Predicate montarPredicate(Root<ModeloDeGasto> root, CriteriaBuilder builder, GastoFiltroDTO dto) {

           Predicate predicate = builder.conjunction();

           if (dto.getCategoria() != null) {
                Predicate predicateCategoria = builder.equal(root.get("categoria"), dto.getCategoria());

                predicate = builder.and(predicate, predicateCategoria);
            }

            if (dto.getValorMaximo() != null) {

                Predicate predicateValorMaximo = builder.lessThanOrEqualTo(root.get("valor"), dto.getValorMaximo());

                predicate = builder.and(predicate, predicateValorMaximo);
            }

            if (dto.getValorMinimo() != null) {

                Predicate predicateValorMinimo = builder.greaterThanOrEqualTo(root.get("valor"), dto.getValorMinimo());
                
                predicate = builder.and(predicate, predicateValorMinimo);
            }

            if (dto.getDataMinima() != null) {
                Predicate predicateDataMinima = builder.greaterThanOrEqualTo(root.get("data"), dto.getDataMinima());

                predicate = builder.and(predicate, predicateDataMinima);
            }

            if (dto.getDataMaxima() != null) {

                Predicate predicateDataMaxima = builder.lessThanOrEqualTo(root.get("data"), dto.getDataMaxima());

                predicate = builder.and(predicate, predicateDataMaxima);
            }
            
            return predicate;
    }
        
}
