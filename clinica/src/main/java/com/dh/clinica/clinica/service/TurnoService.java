package com.dh.clinica.clinica.service;


import com.dh.clinica.clinica.model.Odontologo;
import com.dh.clinica.clinica.model.Paciente;
import com.dh.clinica.clinica.model.Turno;
import com.dh.clinica.clinica.model.dto.TurnoDto;
import com.dh.clinica.clinica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;


    public TurnoDto guardar(Turno turno) {

        Paciente pacienteTurno = pacienteService.buscarPorDni(turno.getPaciente().getDni());
        Odontologo odontologoTurno = odontologoService.buscarPorMatricula(turno.getOdontologo().getMatricula());

        if (pacienteTurno != null) {

            turno.setPaciente(pacienteTurno);
        }

        if (odontologoTurno != null) {

            turno.setOdontologo(odontologoTurno);

        }

        Turno turnoGuardado = turnoRepository.save(turno);

        TurnoDto turnoDto = new TurnoDto();

        turnoDto.setOdontologo(
                turnoGuardado.getOdontologo().getNombre() + " " + turnoGuardado.getOdontologo().getApellido());
        turnoDto.setPaciente(turnoGuardado.getPaciente().getNombre() + " " + turnoGuardado.getPaciente().getApellido());
        turnoDto.setFecha(turnoGuardado.getFecha());

        return turnoDto;

    }

    public List<TurnoDto> buscarTodos() {

        List<Turno> listaTurnos = turnoRepository.findAll();
        List<TurnoDto> listaTurnosDto = new ArrayList<>();

        for (Turno turno : listaTurnos) {

            TurnoDto turnoDto = new TurnoDto();

            turnoDto.setOdontologo(turno.getOdontologo().getNombre() + " " + turno.getOdontologo().getApellido());
            turnoDto.setPaciente(turno.getPaciente().getNombre() + " " + turno.getPaciente().getApellido());
            turnoDto.setFecha(turno.getFecha());

            listaTurnosDto.add(turnoDto);

        }

        return listaTurnosDto;
    }


}
