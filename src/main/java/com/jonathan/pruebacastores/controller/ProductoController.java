package com.jonathan.pruebacastores.controller;

import com.jonathan.pruebacastores.model.Historial;
import com.jonathan.pruebacastores.model.Producto;
import com.jonathan.pruebacastores.repository.HistorialRepository;
import com.jonathan.pruebacastores.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/productos")

public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private HistorialRepository historialRepository;

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody Producto producto) {
        producto.setIdProductos(null);
        Producto newProduct = productoRepository.save(producto);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping("/get-products/{type}")
    public ResponseEntity<?> getProducts(@PathVariable Short type) {
        switch (type) {
            case 0:
                return new ResponseEntity<>(productoRepository.findByEstado((short) 0), HttpStatus.OK);
            case 1:
                return new ResponseEntity<>(productoRepository.findByEstado((short) 1), HttpStatus.OK);
            default:
                return new ResponseEntity<>(productoRepository.findAll(), HttpStatus.OK);
        }
    }

    @PostMapping("/add-quantity/{id}/{quantity}/{idUser}")
    public ResponseEntity<?> addQuantity(@PathVariable Integer id, @PathVariable Integer quantity, @PathVariable Integer idUser) {
        Producto product = productoRepository.findById(id).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        product.setCantidad(product.getCantidad() + quantity);
        productoRepository.save(product);

        Historial historial = new Historial();
        historial.setTipo((short) 1);
        historial.setCantidad(quantity);
        //fecha y hora actual
        historial.setFechaHora(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        historial.setIdProductos(product.getIdProductos());
        historial.setIdUsuario(idUser);
        historialRepository.save(historial);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/remove-quantity/{id}/{quantity}/{idUser}")
    public ResponseEntity<?> removeQuantity(@PathVariable Integer id, @PathVariable Integer quantity, @PathVariable Integer idUser) {
        Producto product = productoRepository.findById(id).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (product.getCantidad() < quantity) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        product.setCantidad(product.getCantidad() - quantity);
        productoRepository.save(product);

        Historial historial = new Historial();
        historial.setTipo((short) 0);
        historial.setCantidad(quantity);
        //fecha y hora actual
        historial.setFechaHora(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        historial.setIdProductos(product.getIdProductos());
        historial.setIdUsuario(idUser);
        historialRepository.save(historial);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/low-product/{id}")
    public ResponseEntity<?> lowProduct(@PathVariable Integer id) {
        Producto product = productoRepository.findById(id).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        product.setEstado((short) 0);
        productoRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/up-product/{id}")
    public ResponseEntity<?> upProduct(@PathVariable Integer id) {
        Producto product = productoRepository.findById(id).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        product.setEstado((short) 1);
        productoRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
