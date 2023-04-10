package com.dh.clinica.clinica.repository;

import com.dh.clinica.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query(value = "select * from Pacientes p WHERE p.dni = ?1", nativeQuery = true)
    Optional<Paciente> findByDni(int dni);

}
