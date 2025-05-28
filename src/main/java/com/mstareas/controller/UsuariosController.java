package com.mstareas.controller;

import com.mstareas.dto.UsuarioDTO;
import com.mstareas.model.pojo.Usuario;
import com.mstareas.model.request.UsuariosRequest;

import io.swagger.v3.oas.annotations.Operation;

import com.mstareas.data.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuariosRepository;

    // 🔽 Convertidor simple (si no usas MapStruct o ModelMapper)
    private UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getName(),
            usuario.getEmail(),
            usuario.getMobile()
        );
    }

    private Usuario toEntity(UsuariosRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setName(dto.getName());
        usuario.setEmail(dto.getEmail());
        usuario.setMobile(dto.getMobile());
        return usuario;
    }

    // 📌 Crear nuevo usuario
    @PostMapping("/usuarios")
    @Operation(summary = "Create a new record")
    public UsuarioDTO crear(@RequestBody UsuariosRequest dto) {
        Usuario nuevo = usuariosRepository.save(toEntity(dto));
        return toDTO(nuevo);
    }

    // 📌 Listar todos los usuarios
    @GetMapping("/usuarios")
    @Operation(summary = "Get all records")
    public List<UsuarioDTO> listarTodos() {
        return usuariosRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // 📌 Buscar por ID
    @GetMapping("/usuarios/{id}")
    @Operation(summary = "Get 1 record per id")
    public UsuarioDTO buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuariosRepository.findById(id);
        return usuarioOpt.map(this::toDTO).orElse(null);
    }

    // 📌 Buscar por nombre (usando @RequestParam)
    // @GetMapping("/search")
    // public UsuarioDTO buscarPorNombre(@RequestParam String name) {
    //     Optional<Usuario> usuarioOpt = usuariosRepository.findByName(name);
    //     return usuarioOpt.map(this::toDTO).orElse(null);
    // }

    // 📌 Actualizar usuario
    @PutMapping("/usuarios/{id}")
    @Operation(summary = "Update a record per id")
    public UsuarioDTO actualizar(@PathVariable Long id, @RequestBody UsuariosRequest dto) {
        Optional<Usuario> usuarioOpt = usuariosRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario existente = usuarioOpt.get();
            existente.setName(dto.getName());
            existente.setEmail(dto.getEmail());
            existente.setMobile(dto.getMobile());
            return toDTO(usuariosRepository.save(existente));
        }
        return null;
    }

    // 📌 Eliminar usuario
    @DeleteMapping("/usuarios/{id}")
    @Operation(summary = "Delete a record per id")
    public void eliminar(@PathVariable Long id) {
        usuariosRepository.deleteById(id);
    }
}

