package com.cice.gestioncarrito.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRestDto {

    private Long idUsuario;
    private String nombre;

}
