package com.jonathan.pruebacastores.repository;

import com.jonathan.pruebacastores.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByCorreoAndContrasena(String correo, String contrasena);
}
