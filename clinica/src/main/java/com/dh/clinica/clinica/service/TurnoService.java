package com.dh.clinica.clinica.service;


import com.dh.clinica.clinica.exceptions.BadRequestException;
import com.dh.clinica.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.clinica.model.Odontologo;
import com.dh.clinica.clinica.model.Paciente;
import com.dh.clinica.clinica.model.Turno;
import com.dh.clinica.clinica.model.dto.TurnoDto;
import com.dh.clinica.clinica.repository.TurnoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService {

    private static final Logger LOGGER = LogManager.getLogger(TurnoService.class);

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;


    public TurnoDto buscarPorId(Long id) throws BadRequestException, ResourceNotFoundException {

        if (id == null || id < 1) {
            throw new BadRequestException("El ID del turno debe ser mayor a 0.");
        }

        Turno turno = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoDto = null;

        if (turno != null) {

            turnoDto = armarTurnoDto(turno);
            LOGGER.info("Se encontró el turno con ID = " + turno.getId());

        } else {
            throw new ResourceNotFoundException("No se encontró el turno con ID = " + id);
        }

        return turnoDto;
    }

    public TurnoDto guardar(Turno turno) throws BadRequestException, ResourceNotFoundException {

        this.validarTurno(turno);

        Paciente pacienteTurno = pacienteService.buscarPorDni(turno.getPaciente().getDni());
        Odontologo odontologoTurno = odontologoService.buscarPorMatricula(turno.getOdontologo().getMatricula());

        if (pacienteTurno != null) {

            turno.setPaciente(pacienteTurno);
        } else {

            pacienteService.guardar(turno.getPaciente());
        }

        if (odontologoTurno != null) {

            turno.setOdontologo(odontologoTurno);

        }

        Turno turnoGuardado = turnoRepository.save(turno);

        LOGGER.info("Se guardó el turno con ID = " + turnoGuardado.getId());

        return this.armarTurnoDto(turno);

    }

    public List<TurnoDto> buscarTodos() {

        List<Turno> listaTurnos = turnoRepository.findAll();
        List<TurnoDto> listaTurnosDto = new ArrayList<>();

        for (Turno turno : listaTurnos) {

            TurnoDto turnoDto = this.armarTurnoDto(turno);

            listaTurnosDto.add(turnoDto);

        }

        LOGGER.info("Se obtuvieron todos los turnos");

        return listaTurnosDto;
    }

    public TurnoDto actualizarTurno(Turno turno, Long id) throws BadRequestException, ResourceNotFoundException {

        this.validarId(id);
        this.validarTurno(turno);

        Turno turnoEncontrado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoDto = null;

        if (turnoEncontrado != null) {

            turno.setId(turnoEncontrado.getId());

            turnoDto = this.guardar(turno);

            LOGGER.info("Se actualizó el turno con ID = " + turnoEncontrado.getId());

        } else {
            throw new ResourceNotFoundException("No se encontró el turno con ID = " + id);
        }

        return turnoDto;

    }

    public void borrarTurno(Long id) throws ResourceNotFoundException, BadRequestException {

        this.validarId(id);

        Turno turno = turnoRepository.findById(id).orElse(null);

        if (turno != null) {

            turnoRepository.deleteById(id);
            LOGGER.info("Se borró el turno con ID = " + turno.getId());

        } else {
            throw new ResourceNotFoundException("El odontólogo con ID = " + id + " no existe");
        }

    }

    public List<TurnoDto> buscarTurnosPorPaciente(int dni) throws BadRequestException {

        this.validarAtributo(dni, "DNI");

        List<Turno> listaTurnos = turnoRepository.findTurnosByPaciente(dni);
        List<TurnoDto> listaTurnosDto = new ArrayList<>();

        for (Turno turno : listaTurnos) {

            TurnoDto turnoDto = this.armarTurnoDto(turno);

            listaTurnosDto.add(turnoDto);

        }

        LOGGER.info("Se obtuvieron todos los turnos para el paciente con DNI = " + dni);

        return listaTurnosDto;
    }

    public List<TurnoDto> buscarTurnosPorOdontologo(int matricula) throws BadRequestException {

        this.validarAtributo(matricula, "Matrícula");

        List<Turno> listaTurnos = turnoRepository.findTurnosByOdontologo(matricula);
        List<TurnoDto> listaTurnosDto = new ArrayList<>();

        for (Turno turno : listaTurnos) {

            TurnoDto turnoDto = this.armarTurnoDto(turno);

            listaTurnosDto.add(turnoDto);

        }

        LOGGER.info("Se obtuvieron todos los turnos para el odontólogo con matrícula = " + matricula);

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

    // VALIDACIONES

    private void validarId(Long id) throws BadRequestException {

        if (id == null || id < 1) {
            throw new BadRequestException("El ID del turno debe ser mayor a 0.");
        }
    }


    /**
     *
     * @param atributo Matrícula o DNI
     * @param tipo Matrícula o DNI
     * @throws BadRequestException Throw exception if attribute < 1
     */
    private void validarAtributo(int atributo, String tipo) throws BadRequestException {

        if (atributo < 1) {

            throw new BadRequestException(tipo + " debe ser mayor a 0.");
        }
    }

    private void validarTurno(Turno turno) throws BadRequestException {

        if (turno == null || turno.getPaciente() == null || turno.getOdontologo() == null || turno.getFecha() == null || turno.getHora() == null) {
            throw new BadRequestException("Falta información del turno");
        }
    }

}
