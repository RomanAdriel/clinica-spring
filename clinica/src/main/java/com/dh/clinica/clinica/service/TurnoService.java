package com.dh.clinica.clinica.service;


import com.dh.clinica.clinica.model.Odontologo;
import com.dh.clinica.clinica.model.Paciente;
import com.dh.clinica.clinica.model.Turno;
import com.dh.clinica.clinica.model.dto.PacienteDto;
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
        } else {

            PacienteDto pacienteGuardado = pacienteService.guardar(turno.getPaciente());
        }

        if (odontologoTurno != null) {

            turno.setOdontologo(odontologoTurno);

        }

        Turno turnoGuardado = turnoRepository.save(turno);

        return this.armarTurnoDto(turno);

    }

    public List<TurnoDto> buscarTodos() {

        List<Turno> listaTurnos = turnoRepository.findAll();
        List<TurnoDto> listaTurnosDto = new ArrayList<>();

        for (Turno turno : listaTurnos) {

            TurnoDto turnoDto = this.armarTurnoDto(turno);

            listaTurnosDto.add(turnoDto);

        }

        return listaTurnosDto;
    }

    public TurnoDto armarTurnoDto(Turno turno) {

        TurnoDto turnoDto = new TurnoDto();

        turnoDto.setOdontologo(turno.getOdontologo().getNombre() + " " + turno.getOdontologo().getApellido());
        turnoDto.setPaciente(turno.getPaciente().getNombre() + " " + turno.getPaciente().getApellido());
        turnoDto.setFecha(turno.getFecha());
        turnoDto.setHora(turno.getHora());

        return turnoDto;

    }


}
