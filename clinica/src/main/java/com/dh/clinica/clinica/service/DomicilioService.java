package com.dh.clinica.clinica.service;

import com.dh.clinica.clinica.exceptions.BadRequestException;
import com.dh.clinica.clinica.model.Domicilio;
import com.dh.clinica.clinica.model.dto.DomicilioDto;
import com.dh.clinica.clinica.repository.DomicilioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class DomicilioService {

    private static final Logger LOGGER = LogManager.getLogger(DomicilioService.class);

    @Autowired
    private DomicilioRepository domicilioRepository;

    public DomicilioDto buscarPorId(Long id) throws BadRequestException {

        this.validarId(id);

        Domicilio domicilio = domicilioRepository.findById(id).orElse(null);

        DomicilioDto domicilioDto = null;

        if (domicilio != null) {

            LOGGER.info("Se encontró el domicilio con ID = " + domicilio.getId());

            domicilioDto = this.armarDomicilioDto(domicilio);

        }

        return domicilioDto;

    }

    public void guardar(@RequestBody Domicilio domicilio) throws BadRequestException {

        this.validarDomicilio(domicilio.getCalle(), domicilio.getNumero(), domicilio.getLocalidad(),
                         domicilio.getProvincia());

        Domicilio domicilioGuardado = domicilioRepository.save(domicilio);

        LOGGER.info("Se guardó el domicilio con ID = " + domicilioGuardado.getId());

    }

    public Domicilio buscarPorDomicilio(String calle, int numero, String localidad, String provincia) throws BadRequestException {

        this.validarDomicilio(calle, numero, localidad, provincia);

        return domicilioRepository.findByCalleAndNumeroAndLocalidadAndProvincia(calle, numero, localidad, provincia);
    }


    public DomicilioDto armarDomicilioDto(Domicilio domicilio) {

        DomicilioDto domicilioDto = new DomicilioDto();

        domicilioDto.setCalle(domicilio.getCalle());
        domicilioDto.setNumero(domicilio.getNumero());
        domicilioDto.setLocalidad(domicilio.getLocalidad());
        domicilioDto.setProvincia(domicilio.getProvincia());
        domicilioDto.setPais(domicilio.getPais());

        return domicilioDto;


    }

    private void validarDomicilio(String calle, int numero, String localidad, String provincia) throws BadRequestException {

        if (calle == null || numero < 1 || localidad == null || provincia == null) {
            throw new BadRequestException("Falta información del domicilio");
        }


    }

    private void validarId(Long id) throws BadRequestException {
        if (id == null || id < 1) {
            throw new BadRequestException("El ID debe ser mayor a 0");
        }
    }


}
