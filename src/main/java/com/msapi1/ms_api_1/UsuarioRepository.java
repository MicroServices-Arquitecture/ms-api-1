package com.msapi1.ms_api_1;

import com.msapi1.ms_api_1.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
}