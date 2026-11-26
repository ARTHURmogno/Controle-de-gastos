package com.UMBRELLA.ControleDeGastos.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.UMBRELLA.ControleDeGastos.dto.GastoFiltroDTO;
import com.UMBRELLA.ControleDeGastos.dto.ResumoMensalDTO;
import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;
import com.UMBRELLA.ControleDeGastos.specification.BuscaDinamica;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository 
public class ResumoDeGastosRepository {
    
    @PersistenceContext
    private EntityManager em;

    public List<ResumoMensalDTO> resumoMensal(GastoFiltroDTO filtro) {

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> query = builder.createTupleQuery();

        Root<ModeloDeGasto> root = query.from(ModeloDeGasto.class);

        Expression<Integer> ano = builder.function("year", Integer.class, root.get("data"));
        Expression<Integer> mes = builder.function("month", Integer.class, root.get("data"));
        Expression<BigDecimal> total = builder.sum(root.get("valor"));

        query.multiselect(
            ano.alias("ano"),
             mes.alias("mes"),
              total.alias("total")
        );

        query.where(BuscaDinamica.montarPredicate(root, builder, filtro));

        query.groupBy(ano, mes);

        query.orderBy(builder.asc(ano), builder.asc(mes));

        List<Tuple> linhas = em.createQuery(query).getResultList();

        List<ResumoMensalDTO> resultado = new ArrayList<>();
        for (Tuple linha : linhas) {

            Integer anoLido = linha.get("ano", Integer.class);
            Integer mesLido = linha.get("mes", Integer.class);
            BigDecimal totalLido = linha.get("total", BigDecimal.class);

            ResumoMensalDTO resumo = new ResumoMensalDTO(anoLido, mesLido, totalLido);
            
            resultado.add(resumo);
        }

        return resultado;
    }
    
}
