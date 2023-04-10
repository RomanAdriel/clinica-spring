package com.dh.clinica.clinica.service;

import com.dh.clinica.clinica.exceptions.BadRequestException;
import com.dh.clinica.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.clinica.model.Domicilio;
import com.dh.clinica.clinica.model.Paciente;
import com.dh.clinica.clinica.model.dto.DomicilioDto;
import com.dh.clinica.clinica.model.dto.PacienteDto;
import com.dh.clinica.clinica.repository.PacienteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {

    private static final Logger LOGGER = LogManager.getLogger(PacienteService.class);

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DomicilioService domicilioService;

    public PacienteDto buscarPorId(Long id) {

        Paciente paciente = pacienteRepository.findById(id).orElse(null);

        PacienteDto pacienteDto = null;

        if (paciente != null) {

            DomicilioDto domicilioDto = domicilioService.armarDomicilioDto(paciente.getDomicilio());
            pacienteDto = this.armarPacienteDto(paciente, domicilioDto);

            LOGGER.info("Se encontró el paciente con ID = " + paciente.getId());

        }

        return pacienteDto;

    }

    public PacienteDto guardar(Paciente paciente) throws BadRequestException {

        this.validarPaciente(paciente);

        Domicilio domicilioPaciente = domicilioService.buscarPorDomicilio(paciente.getDomicilio().getCalle(),
                                                                          paciente.getDomicilio().getNumero(),
                                                                          paciente.getDomicilio().getLocalidad(),
                                                                          paciente.getDomicilio().getProvincia());

        if (domicilioPaciente == null) {

            domicilioService.guardar(paciente.getDomicilio());
        } else {
            paciente.setDomicilio(domicilioPaciente);
        }

        Paciente pacienteGuardado = pacienteRepository.save(paciente);

        LOGGER.info("Se guardó el paciente con ID = " + pacienteGuardado.getId());

        DomicilioDto domicilioDto = domicilioService.armarDomicilioDto(paciente.getDomicilio());

        return this.armarPacienteDto(pacienteGuardado, domicilioDto);

    }


    public PacienteDto actualizar(Paciente paciente, Long id) throws BadRequestException {

        this.validarPaciente(paciente);


        Paciente pacienteEncontrado = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteDto = null;

        if (pacienteEncontrado != null) {

            paciente.setId(pacienteEncontrado.getId());

            pacienteDto = this.guardar(paciente);

            LOGGER.info("Se actualizó el paciente con ID = " + pacienteEncontrado.getId());

        }

        return pacienteDto;

    }

    public List<PacienteDto> buscarTodos() {

        List<Paciente> listaPacientes = pacienteRepository.findAll();
        List<PacienteDto> listaPacientesDto = new ArrayList<>();

        for (Paciente paciente : listaPacientes) {

            DomicilioDto domicilioDto = domicilioService.armarDomicilioDto(paciente.getDomicilio());
            PacienteDto pacienteDto = this.armarPacienteDto(paciente, domicilioDto);

            listaPacientesDto.add(pacienteDto);

        }

        LOGGER.info("Se obtuvieron todos los pacientes");

        return listaPacientesDto;
    }

    public void borrarPorId(Long id) throws ResourceNotFoundException, BadRequestException {

        this.validarId(id);

        Paciente paciente = pacienteRepository.findById(id).orElse(null);

        if (paciente != null) {

            pacienteRepository.deleteById(id);
            LOGGER.info("Se borró el paciente con ID = " + paciente.getId());

        } else {
            throw new ResourceNotFoundException("El odontólogo con ID = " + id + " no existe");
        }

    }


    public Paciente buscarPorDni(int dni) throws BadRequestException, ResourceNotFoundException {

        this.validarDni(dni);

        Paciente paciente = pacienteRepository.findByDni(dni).orElse(null);

        if (paciente != null) {
            return paciente;
        } else {
            throw new ResourceNotFoundException("El paciente con DNI = " + dni + " no existe.");
        }
        
    }

    public PacienteDto armarPacienteDto(Paciente paciente, DomicilioDto domicilioDto) {

        PacienteDto pacienteDto = new PacienteDto();

        pacienteDto.setNombre(paciente.getNombre());
        pacienteDto.setApellido(paciente.getApellido());
        pacienteDto.setDomicilio(domicilioDto);

        return pacienteDto;

    }

    // VALIDACIONES

    private void validarId(Long id) throws BadRequestException {
        if (id == null || id < 1) {
            throw new BadRequestException("El ID debe ser mayor a 0");
        }
    }

    private void validarPaciente(Paciente paciente) throws BadRequestException {

        if (paciente == null || paciente.getDomicilio() == null || paciente.getFechaAlta() == null || paciente.getNombre() == null || paciente.getApellido() == null || paciente.getDni() < 1) {
            throw new BadRequestException("Falta información del paciente");
        }
    }

    private void validarDni(int dni) throws BadRequestException {

        if (dni < 1) {
            throw new BadRequestException("El DNI del paciente debe ser mayor a 0");
        }
    }


}
