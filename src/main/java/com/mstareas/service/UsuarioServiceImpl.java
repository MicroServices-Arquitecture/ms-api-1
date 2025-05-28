package com.mstareas.service;

import org.springframework.stereotype.Service;

import com.mstareas.dto.UsuarioDTO;
import com.mstareas.model.pojo.Usuario;
import com.mstareas.model.request.UsuariosRequest;

@Service
public class UsuarioServiceImpl {

    

    // Convertidor simple.
    public UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getName(),
            usuario.getEmail(),
            usuario.getMobile()
        );
    }

    public Usuario toEntity(UsuariosRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setName(dto.getName());
        usuario.setEmail(dto.getEmail());
        usuario.setMobile(dto.getMobile());
        return usuario;
    }

    public void updateEntity(Usuario usuario, UsuariosRequest dto){
        usuario.setName(dto.getName());
        usuario.setEmail(dto.getEmail());
        usuario.setMobile(dto.getMobile());
    }
}
