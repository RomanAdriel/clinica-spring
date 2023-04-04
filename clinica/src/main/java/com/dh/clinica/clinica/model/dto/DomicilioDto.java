package com.dh.clinica.clinica.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomicilioDto {

    private String calle;
    private int numero;
    private String localidad;
    private String provincia;
    private String pais;


}
