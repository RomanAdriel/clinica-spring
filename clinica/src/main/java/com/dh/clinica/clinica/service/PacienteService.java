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

            pacienteDto = new PacienteDto();
            domicilioDto = new DomicilioDto();


            domicilioDto.setCalle(paciente.getDomicilio().getCalle());
            domicilioDto.setNumero(paciente.getDomicilio().getNumero());
            domicilioDto.setLocalidad(paciente.getDomicilio().getLocalidad());
            domicilioDto.setProvincia(paciente.getDomicilio().getProvincia());
            domicilioDto.setPais(paciente.getDomicilio().getPais());
            pacienteDto.setApellido(paciente.getApellido());
            pacienteDto.setNombre(paciente.getNombre());
            pacienteDto.setDomicilio(domicilioDto);
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

        PacienteDto pacienteDto = new PacienteDto();
        DomicilioDto domicilioDto = new DomicilioDto();

        domicilioDto.setCalle(paciente.getDomicilio().getCalle());
        domicilioDto.setNumero(paciente.getDomicilio().getNumero());
        domicilioDto.setLocalidad(paciente.getDomicilio().getLocalidad());
        domicilioDto.setProvincia(paciente.getDomicilio().getProvincia());
        domicilioDto.setPais(paciente.getDomicilio().getPais());

        pacienteDto.setNombre(pacienteGuardado.getNombre());
        pacienteDto.setApellido(pacienteGuardado.getApellido());
        pacienteDto.setDomicilio(domicilioDto);

        return pacienteDto;

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

            PacienteDto pacienteDto = new PacienteDto();
            DomicilioDto domicilioDto = new DomicilioDto();

            domicilioDto.setCalle(paciente.getDomicilio().getCalle());
            domicilioDto.setNumero(paciente.getDomicilio().getNumero());
            domicilioDto.setLocalidad(paciente.getDomicilio().getLocalidad());
            domicilioDto.setProvincia(paciente.getDomicilio().getProvincia());
            domicilioDto.setPais(paciente.getDomicilio().getPais());
            pacienteDto.setNombre(paciente.getNombre());
            pacienteDto.setApellido(paciente.getApellido());
            pacienteDto.setDomicilio(domicilioDto);

            listaPacientesDto.add(pacienteDto);

        }

        return listaPacientesDto;
    }

    public void borrarPorId(Long id) {


        pacienteRepository.deleteById(id);

    }

//    public PacienteDto buscarPorDni(int dni) {
//
//
//        Paciente paciente = pacienteRepository.findByDni(dni).orElse(null);
//
//        PacienteDto pacienteDto = null;
//        DomicilioDto domicilioDto = null;
//
//        if (paciente != null) {
//
//            LOGGER.info("Se crea DTO");
//
//            pacienteDto = new PacienteDto();
//            domicilioDto = new DomicilioDto();
//
//
//            domicilioDto.setCalle(paciente.getDomicilio().getCalle());
//            domicilioDto.setNumero(paciente.getDomicilio().getNumero());
//            domicilioDto.setLocalidad(paciente.getDomicilio().getLocalidad());
//            domicilioDto.setProvincia(paciente.getDomicilio().getProvincia());
//            domicilioDto.setPais(paciente.getDomicilio().getPais());
//            pacienteDto.setApellido(paciente.getApellido());
//            pacienteDto.setNombre(paciente.getNombre());
//            pacienteDto.setDomicilio(domicilioDto);
//        }
//
//        return pacienteDto;
//
//    }

    public Paciente buscarPorDni(int dni) {


        return pacienteRepository.findByDni(dni).orElse(null);

    }


}
