package com.UMBRELLA.ControleDeGastos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;


public interface ModeloDeGastosRepository extends JpaRepository<ModeloDeGasto, Long>, JpaSpecificationExecutor<ModeloDeGasto> {

    boolean existsById(Long id);


}
