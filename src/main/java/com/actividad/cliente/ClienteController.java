package com.actividad.cliente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @Value("hola")
    private String UsuariosTareas;

    @GetMapping("/mostrar-config")
    public String mostrarConfig() {
        return "El valor de foo es: " + UsuariosTareas;
    }
}

