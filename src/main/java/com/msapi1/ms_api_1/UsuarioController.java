package com.msapi1.ms_api_1;

import com.msapi1.ms_api_1.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @GetMapping("/buscar")
    public List<Usuario> buscarPorNombre(@RequestParam String nombre) {
        return usuarioService.buscarPorNombre(nombre);
    }
}
