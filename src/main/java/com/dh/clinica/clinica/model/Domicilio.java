package com.dh.clinica.clinica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "domicilios")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceDomicilio")
    @SequenceGenerator(name = "SequenceDomicilio", sequenceName = "SequenceDomicilio", allocationSize = 1)
    private Long id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;
    private String pais;
    @OneToMany(mappedBy = "domicilio", cascade = CascadeType.ALL)
    private Set<Paciente> pacientes = new HashSet<>();
}
