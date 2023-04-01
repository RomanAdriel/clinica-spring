package com.dh.clinica.clinica.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PacienteDto {

    private String nombre;
    private String apellido;
    private Long domicilio;
}
