package com.mstareas.controller;

import com.mstareas.dto.UsuarioDTO;
import com.mstareas.model.pojo.Usuario;
import com.mstareas.model.request.UsuariosRequest;
import com.mstareas.service.UsuarioServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

import com.mstareas.data.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuarioRepository usuariosRepository;
    private final UsuarioServiceImpl usuarioServiceImpl;

    public UsuariosController(UsuarioRepository usuariosRepository, UsuarioServiceImpl usuarioServiceImpl){
        this.usuariosRepository = usuariosRepository;
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    // Crear nuevo usuario
    @PostMapping("/taskuser")
    @Operation(summary = "Create a new record")
    public ResponseEntity<UsuarioDTO> createUser(@RequestBody UsuariosRequest dto) {
        Usuario usuario = usuariosRepository.save(usuarioServiceImpl.toEntity(dto));
        return ResponseEntity.ok(usuarioServiceImpl.toDTO(usuario));
    }

    // Listar todos los usuarios
    @GetMapping("/taskuser")
    @Operation(summary = "Get all records")
    public List<UsuarioDTO> listAll() {
        return usuariosRepository.findAll().stream()
                .map(usuarioServiceImpl::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar por id
    @GetMapping("/taskuser/id/{id}")
    @Operation(summary = "Get 1 record per id")
    public ResponseEntity<UsuarioDTO> getUser(@PathVariable Long id) {
        
        return usuariosRepository.findById(id).map(usuarioServiceImpl::toDTO)
               .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar por name
    @GetMapping("/taskuser/name/{name}")
    @Operation(summary = "Get 1 record per name")
    public ResponseEntity <UsuarioDTO> getName(@RequestParam String name) {

        return usuariosRepository.findByName(name).map(usuarioServiceImpl::toDTO)
               .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar usuario
    @PutMapping("/taskuser/id/{id}")
    @Operation(summary = "Update a record per id")
    public ResponseEntity<UsuarioDTO> updateUser(@PathVariable Long id, @RequestBody UsuariosRequest dto) {

        return usuariosRepository.findById(id).map(userCreated -> {
            usuarioServiceImpl.updateEntity(userCreated, dto);
            Usuario saved = usuariosRepository.save(userCreated);
            return ResponseEntity.ok(usuarioServiceImpl.toDTO(saved));
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar usuario
    @DeleteMapping("/taskuser/{id}")
    @Operation(summary = "Delete a record per id")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (usuariosRepository.existsById(id)){
            usuariosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

