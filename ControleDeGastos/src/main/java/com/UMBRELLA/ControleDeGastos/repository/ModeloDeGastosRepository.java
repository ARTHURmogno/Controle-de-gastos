package com.UMBRELLA.ControleDeGastos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;

public interface ModeloDeGastosRepository extends JpaRepository<ModeloDeGasto, Long> {

    boolean existsById(Long id);


}
