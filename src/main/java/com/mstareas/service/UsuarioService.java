package com.mstareas.service;

import org.springframework.stereotype.Service;

import com.mstareas.model.pojo.Usuario;
import com.mstareas.data.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario findByName(String name) {
        return usuarioRepository.findByName(name).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setName(usuarioDetails.getName());
            usuario.setEmail(usuarioDetails.getEmail());
            usuario.setMobile(usuarioDetails.getMobile());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}

