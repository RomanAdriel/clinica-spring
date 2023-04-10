package com.dh.clinica.clinica.service;

import com.dh.clinica.clinica.exceptions.BadRequestException;
import com.dh.clinica.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.clinica.model.Odontologo;
import com.dh.clinica.clinica.model.dto.OdontologoDto;
import com.dh.clinica.clinica.repository.OdontologoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoService {

    private static final Logger LOGGER = LogManager.getLogger(OdontologoService.class);

    @Autowired
    private OdontologoRepository odontologoRepository;


    public OdontologoDto buscarPorId(Long id) throws BadRequestException, ResourceNotFoundException {

        this.validarId(id);

        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);

        OdontologoDto odontologoDto = null;

        if (odontologo != null) {

            odontologoDto = this.armarOdontologoDto(odontologo);
            LOGGER.info("Se encontró el odontólogo con ID = " + odontologo.getId());
        } else {
            throw new ResourceNotFoundException("No se encontró el odontólogo.");
        }

        return odontologoDto;
    }


    public OdontologoDto guardar(Odontologo odontologo) throws BadRequestException {

        this.validarOdontologo(odontologo);

        Odontologo odontologoGuardado = odontologoRepository.save(odontologo);
        LOGGER.info("Se guardó el odontólogo con ID = " + odontologoGuardado.getId());

        return this.armarOdontologoDto(odontologoGuardado);

    }

    public OdontologoDto actualizar(Odontologo odontologo, Long id) throws BadRequestException, ResourceNotFoundException {

        this.validarId(id);

        this.validarOdontologo(odontologo);

        Odontologo odontologoEncontrado = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto = null;

        if (odontologoEncontrado != null) {

            odontologo.setId(odontologoEncontrado.getId());

            odontologoDto = this.guardar(odontologo);

            LOGGER.info("Se actualizó el odontólogo con ID = " + odontologoEncontrado.getId());

        } else {
            throw new ResourceNotFoundException("No se encontró el odontólogo.");
        }

        return odontologoDto;

    }

    public List<OdontologoDto> buscarTodos() {

        List<Odontologo> listaOdontologos = odontologoRepository.findAll();
        List<OdontologoDto> listaOdontologosDto = new ArrayList<>();

        for (Odontologo odontologo : listaOdontologos) {

            OdontologoDto odontologoDto = this.armarOdontologoDto(odontologo);

            listaOdontologosDto.add(odontologoDto);

        }

        LOGGER.info("Se obtuvieron todos los odontólogos");

        return listaOdontologosDto;
    }

    public void borrarPorId(Long id) throws ResourceNotFoundException, BadRequestException {

        this.validarId(id);

        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);

        if (odontologo != null) {

            odontologoRepository.deleteById(id);
            LOGGER.info("Se borró el odontólogo con ID = " + odontologo.getId());

        } else {
            throw new ResourceNotFoundException("El odontólogo con ID = " + id + " no existe");
        }


    }

    public Odontologo buscarPorMatricula(int matricula) throws BadRequestException, ResourceNotFoundException {

        this.validarMatricula(matricula);

        Odontologo odontologo = odontologoRepository.findByMatricula(matricula).orElse(null);

        if(odontologo != null) {
            return odontologo;
        } else {
            throw new ResourceNotFoundException("El odontólogo con matrícula " + matricula + " no existe.");
        }
    }

    public OdontologoDto armarOdontologoDto(Odontologo odontologo) {

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre(odontologo.getNombre());
        odontologoDto.setApellido(odontologo.getApellido());
        odontologoDto.setMatricula(odontologo.getMatricula());

        return odontologoDto;
    }

    // VALIDACIONES

    private void validarId(Long id) throws BadRequestException {

        if (id == null || id < 1) {
            throw new BadRequestException("El ID debe ser mayor a 0");
        }
    }

    private void validarOdontologo(Odontologo odontologo) throws BadRequestException {

        if (odontologo == null || odontologo.getNombre() == null || odontologo.getApellido() == null || odontologo.getMatricula() < 1) {
            throw new BadRequestException("Falta información del odontólogo.");
        }

    }

    private void validarMatricula(int matricula) throws BadRequestException {

        if (matricula < 1) {

            throw new BadRequestException("La matrícula debe ser mayor a 0.");
        }
    }
}