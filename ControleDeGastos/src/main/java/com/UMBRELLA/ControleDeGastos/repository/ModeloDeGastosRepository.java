package com.UMBRELLA.ControleDeGastos.repository;


import java.util.List;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.UMBRELLA.ControleDeGastos.model.ModeloDeGasto;

public interface ModeloDeGastosRepository extends JpaRepository<ModeloDeGasto, Long> {

    boolean existsById(Long id);

    List<ModeloDeGasto> findAllOrDenByNome(Pageable pageable);

}
