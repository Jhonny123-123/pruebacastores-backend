package com.jonathan.pruebacastores.controller;

import com.jonathan.pruebacastores.model.Usuario;
import com.jonathan.pruebacastores.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login/{correo}/{contrasena}")
    public ResponseEntity<?> login(@PathVariable String correo, @PathVariable String contrasena) {
        Usuario usuario = usuarioRepository.findByCorreoAndContrasena(correo, contrasena);
        if (usuario == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
