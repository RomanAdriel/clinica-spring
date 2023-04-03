package com.dh.clinica.clinica.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pacientes", indexes = {@Index(name = "dni_unique_index", columnList = "dni", unique = true)})
//@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequencePaciente")
    @SequenceGenerator(name = "SequencePaciente", sequenceName = "SequencePaciente", allocationSize = 1)
    private Long id;
    private String nombre;
    private String apellido;
    private int dni;
    @Column(name = "fecha_alta")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaAlta;
    @ManyToOne(fetch = FetchType.EAGER)
    private Domicilio domicilio;
}
