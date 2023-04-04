package com.dh.clinica.clinica.service;

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
        DomicilioDto domicilioDto = null;

        if (paciente != null) {

            LOGGER.info("Se crea DTO");

            domicilioDto = domicilioService.armarDomicilioDto(paciente.getDomicilio());
            pacienteDto = this.armarPacienteDto(paciente, domicilioDto);

        }

        return pacienteDto;

    }

    public PacienteDto guardar(Paciente paciente) {

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

        DomicilioDto domicilioDto = domicilioService.armarDomicilioDto(paciente.getDomicilio());

        return this.armarPacienteDto(pacienteGuardado, domicilioDto);

    }


    public PacienteDto actualizar(Paciente paciente, Long id) {

        Paciente pacienteEncontrado = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteDto = null;

        if (pacienteEncontrado != null) {

            paciente.setId(pacienteEncontrado.getId());

            pacienteDto = this.guardar(paciente);

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

        return listaPacientesDto;
    }

    public void borrarPorId(Long id) {


        pacienteRepository.deleteById(id);

    }


    public Paciente buscarPorDni(int dni) {


        return pacienteRepository.findByDni(dni).orElse(null);

    }

    public PacienteDto armarPacienteDto(Paciente paciente, DomicilioDto domicilioDto) {

        PacienteDto pacienteDto = new PacienteDto();

        pacienteDto.setNombre(paciente.getNombre());
        pacienteDto.setApellido(paciente.getApellido());
        pacienteDto.setDomicilio(domicilioDto);

        return pacienteDto;

    }


}
