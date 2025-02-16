package com.jonathan.pruebacastores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(name = "historial")
@Getter
@Setter
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistorial;
    private Short tipo;
    private Integer cantidad;
    private String fechaHora;
    private Integer idProductos;
    private Integer idUsuario;

}
