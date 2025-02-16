package com.jonathan.pruebacastores.controller;

import com.jonathan.pruebacastores.model.Historial;
import com.jonathan.pruebacastores.model.HistorialDetalle;
import com.jonathan.pruebacastores.model.Producto;
import com.jonathan.pruebacastores.model.Usuario;
import com.jonathan.pruebacastores.repository.HistorialRepository;
import com.jonathan.pruebacastores.repository.ProductoRepository;
import com.jonathan.pruebacastores.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    private HistorialRepository historialRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;


    @GetMapping("/get-historial/{type}")
    public ResponseEntity<?> getHistorial(@PathVariable Short type) {
        List<Historial> historialList = (type == 0 || type == 1) ? historialRepository.findByTipo(type) : historialRepository.findAll();
        List<HistorialDetalle> historialDetalleList = new ArrayList<>();

        for(Historial historial : historialList){
            Usuario usuario = usuarioRepository.findById(historial.getIdUsuario()).orElse(null);
            if(usuario == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            Producto producto = productoRepository.findById(historial.getIdProductos()).orElse(null);
            if(producto == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            HistorialDetalle historialDetalle = new HistorialDetalle();
            historialDetalle.setIdHistorial(historial.getIdHistorial());
            historialDetalle.setTipo(historial.getTipo());
            historialDetalle.setCantidad(historial.getCantidad());
            historialDetalle.setFechaHora(historial.getFechaHora());
            historialDetalle.setNombreProductos(producto.getNombre());
            historialDetalle.setNombreUsuario(usuario.getNombre());
            historialDetalleList.add(historialDetalle);
        }

        return new ResponseEntity<>(historialDetalleList, HttpStatus.OK);
    }

}
