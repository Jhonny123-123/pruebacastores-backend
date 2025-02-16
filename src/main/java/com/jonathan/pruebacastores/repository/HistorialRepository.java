package com.jonathan.pruebacastores.repository;

import com.jonathan.pruebacastores.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialRepository extends JpaRepository<Historial, Integer> {

    List<Historial> findByTipo(Short tipo);
}
