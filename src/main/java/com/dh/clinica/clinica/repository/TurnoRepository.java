package com.dh.clinica.clinica.repository;

import com.dh.clinica.clinica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    @Query(value = "select t.* from Pacientes p left join Turnos t where p.dni = ?1",
            nativeQuery = true)
    List<Turno> findTurnosByPaciente(int dni);

    @Query(value = "select t.* from Odontologos o left join Turnos t where o.matricula = ?1",
            nativeQuery = true)
    List<Turno> findTurnosByOdontologo(int matricula);
}
