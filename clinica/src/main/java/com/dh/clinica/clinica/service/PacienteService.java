package com.dh.clinica.clinica.service;

import com.dh.clinica.clinica.model.Paciente;
import com.dh.clinica.clinica.model.dto.PacienteDto;
import com.dh.clinica.clinica.repository.PacienteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {

    private static final Logger LOGGER = LogManager.getLogger(PacienteService.class);

    @Autowired
    private PacienteRepository pacienteRepository;

//    @Autowired
//    private DomicilioService domicilioService;

    public PacienteDto buscarPorId(Long id) {

        Paciente paciente = pacienteRepository.findById(id).orElse(null);

        PacienteDto pacienteDto = null;

        if (paciente != null) {

            LOGGER.info("Se crea DTO");

            pacienteDto = new PacienteDto();

            pacienteDto.setApellido(paciente.getApellido());
            pacienteDto.setNombre(paciente.getNombre());
            pacienteDto.setDomicilio(paciente.getDomicilio());
        }

        return pacienteDto;

    }

    public PacienteDto guardar(@RequestBody Paciente paciente) {

//        DomicilioDto domicilioPaciente = domicilioService.buscarPorId(paciente.getDomicilio().getId());
//
//        if (domicilioPaciente == null) {
//
//            domicilioService.guardar(paciente.getDomicilio());
//        }

        Paciente pacienteGuardado = pacienteRepository.save(paciente);

        PacienteDto pacienteDto = new PacienteDto();

        pacienteDto.setNombre(pacienteGuardado.getNombre());
        pacienteDto.setApellido(pacienteGuardado.getApellido());
        pacienteDto.setDomicilio(pacienteGuardado.getDomicilio());

        return pacienteDto;

    }


    public PacienteDto actualizar(@RequestBody Paciente paciente, Long id) {

        PacienteDto pacienteEncontrado = this.buscarPorId(id);
        PacienteDto pacienteActualizado = null;

        if (pacienteEncontrado != null) {
            pacienteActualizado = this.guardar(paciente);

        }

        return pacienteActualizado;

    }

    public List<PacienteDto> buscarTodos() {

        List<Paciente> listaPacientes = pacienteRepository.findAll();
        List<PacienteDto> listaPacientesDto = new ArrayList<>();

        for (Paciente paciente : listaPacientes) {

            PacienteDto pacienteDto = new PacienteDto();
            pacienteDto.setNombre(paciente.getNombre());
            pacienteDto.setApellido(paciente.getApellido());
            pacienteDto.setDomicilio(paciente.getDomicilio());

            listaPacientesDto.add(pacienteDto);

        }

        return listaPacientesDto;
    }

    public void borrarPorId(Long id) {


        pacienteRepository.deleteById(id);

    }


}
