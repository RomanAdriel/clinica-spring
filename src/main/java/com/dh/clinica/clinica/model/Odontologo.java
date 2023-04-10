package com.dh.clinica.clinica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "odontologos", indexes = {@Index(name = "matricula_unique_index", columnList = "matricula", unique =
        true)})
public class Odontologo {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceOdontologo")
    @SequenceGenerator(name = "SequenceOdontologo", sequenceName = "SequenceOdontologo", allocationSize = 1)
    private Long id;
    private String nombre;
    private String apellido;
    private int matricula;

}
