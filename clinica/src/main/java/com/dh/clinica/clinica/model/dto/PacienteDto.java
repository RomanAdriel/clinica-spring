package com.dh.clinica.clinica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDto {

    private String nombre;
    private String apellido;
    private DomicilioDto domicilio;
}
