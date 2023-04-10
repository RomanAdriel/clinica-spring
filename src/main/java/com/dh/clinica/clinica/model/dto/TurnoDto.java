package com.dh.clinica.clinica.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class TurnoDto {

    private String odontologo;
    private String paciente;
    private LocalDate fecha;
    private LocalTime hora;

}
