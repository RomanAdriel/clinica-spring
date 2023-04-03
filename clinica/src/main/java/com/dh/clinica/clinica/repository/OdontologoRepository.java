package com.dh.clinica.clinica.repository;

import com.dh.clinica.clinica.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

    @Query(value = "select * from Odontologos d WHERE d.matricula = ?1", nativeQuery = true)
    Optional<Odontologo> findByMatricula(int matricula);
}
