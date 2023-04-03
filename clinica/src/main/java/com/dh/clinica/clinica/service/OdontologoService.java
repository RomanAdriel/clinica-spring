package com.dh.clinica.clinica.service;

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


    public OdontologoDto buscarPorId(Long id) {

        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);

        OdontologoDto odontologoDto = null;

        if (odontologo != null) {

            LOGGER.info("Se crea DTO");

            odontologoDto = new OdontologoDto();

            odontologoDto.setApellido(odontologo.getApellido());
            odontologoDto.setNombre(odontologo.getNombre());
            odontologoDto.setMatricula(odontologo.getMatricula());
        }

        return odontologoDto;
    }


    public OdontologoDto guardar(Odontologo odontologo) {

        Odontologo odontologoGuardado = odontologoRepository.save(odontologo);

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setApellido(odontologo.getApellido());
        odontologoDto.setNombre(odontologo.getNombre());
        odontologoDto.setMatricula(odontologo.getMatricula());

        return odontologoDto;

    }

    public OdontologoDto actualizar(Odontologo odontologo, Long id) {

        Odontologo odontologoEncontrado = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto = null;

        if (odontologoEncontrado != null) {

            odontologo.setId(odontologoEncontrado.getId());

            odontologoDto = this.guardar(odontologo);

        }

        return odontologoDto;

    }

    public List<OdontologoDto> buscarTodos() {

        List<Odontologo> listaOdontologos = odontologoRepository.findAll();
        List<OdontologoDto> listaOdontologosDto = new ArrayList<>();

        for (Odontologo odontologo : listaOdontologos) {

            OdontologoDto odontologoDto = new OdontologoDto();

            odontologoDto.setNombre(odontologo.getNombre());
            odontologoDto.setApellido(odontologo.getApellido());
            odontologoDto.setMatricula(odontologo.getMatricula());

            listaOdontologosDto.add(odontologoDto);

        }

        return listaOdontologosDto;
    }

    public void borrarPorId(Long id) {


        odontologoRepository.deleteById(id);

    }

//    public OdontologoDto buscarPorMatricula(int matricula) {
//
//        Odontologo odontologo = odontologoRepository.findByMatricula(matricula).orElse(null);
//
//        OdontologoDto odontologoDto = null;
//
//        if (odontologo != null) {
//
//            LOGGER.info("Se crea DTO");
//
//            odontologoDto = new OdontologoDto();
//
//            odontologoDto.setApellido(odontologo.getApellido());
//            odontologoDto.setNombre(odontologo.getNombre());
//            odontologoDto.setMatricula(odontologo.getMatricula());
//        }
//
//        return odontologoDto;
//    }

    public Odontologo buscarPorMatricula(int matricula) {

        return odontologoRepository.findByMatricula(matricula).orElse(null);
    }
}