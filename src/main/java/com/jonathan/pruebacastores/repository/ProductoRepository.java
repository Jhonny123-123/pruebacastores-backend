package com.jonathan.pruebacastores.repository;

import com.jonathan.pruebacastores.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByEstado(Short estado);
}
