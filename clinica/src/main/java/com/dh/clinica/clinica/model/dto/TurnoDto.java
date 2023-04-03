package com.dh.clinica.clinica.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TurnoDto {

    public String odontologo;
    public String paciente;
    public Date fecha;

}
