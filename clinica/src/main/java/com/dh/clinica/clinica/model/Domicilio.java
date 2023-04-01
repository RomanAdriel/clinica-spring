//package com.dh.clinica.clinica.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//public class Domicilio {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sequence")
//    @SequenceGenerator(name = "Sequence", sequenceName = "Sequence")
//    private Long id;
//    private String calle;
//    private int numero;
//    private String localidad;
//    private String provincia;
//    private String pais;
//    @OneToMany(mappedBy = "domicilio", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Paciente> pacientes = new HashSet<>();
//}
