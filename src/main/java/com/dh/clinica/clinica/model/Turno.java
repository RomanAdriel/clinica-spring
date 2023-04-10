package com.dh.clinica.clinica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceTurnos")
    @SequenceGenerator(name = "SequenceTurnos", sequenceName = "SequenceTurnos", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="odontologo_id")
    private Odontologo odontologo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDate fecha;
    private LocalTime hora;
}
