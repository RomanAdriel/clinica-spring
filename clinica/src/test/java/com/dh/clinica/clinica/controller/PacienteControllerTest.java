package com.dh.clinica.clinica.controller;

import com.dh.clinica.clinica.model.dto.DomicilioDto;
import com.dh.clinica.clinica.model.dto.PacienteDto;
import com.dh.clinica.clinica.service.DomicilioService;
import com.dh.clinica.clinica.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PacienteControllerTest {

    @InjectMocks
    PacienteController pacienteController;

    @Mock
    PacienteService pacienteService;
    @Mock
    DomicilioService domicilioService;

    @Test
    void buscar_paciente_por_id_no_existente() {

        //ARRANGE

        ResponseEntity<PacienteDto> entidadEsperada =  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        PacienteDto pacienteDto = null;

        //ACT

        when(pacienteService.buscarPorId(any())).thenReturn(pacienteDto);

        //ASSERT

        Assertions.assertEquals(entidadEsperada, pacienteController.buscarPacientePorId(1L));
    }


    @Test
    void buscar_paciente_por_id_existente() {

        //ARRANGE

        DomicilioDto domicilioDto = new DomicilioDto();
        domicilioDto.setCalle("Fake St.");
        domicilioDto.setNumero(123);
        domicilioDto.setLocalidad("Springfield");
        domicilioDto.setProvincia("Massachussets");
        domicilioDto.setPais("United States");

        PacienteDto pacienteDto = new PacienteDto();

        pacienteDto.setNombre("Pepito");
        pacienteDto.setApellido("Perez");
        pacienteDto.setDomicilio(domicilioDto);

        ResponseEntity<PacienteDto> entidadEsperada =  ResponseEntity.ok(pacienteDto);


        //ACT

        when(pacienteService.buscarPorId(any())).thenReturn(pacienteDto);

        //ASSERT

        Assertions.assertEquals(entidadEsperada, pacienteController.buscarPacientePorId(1L));
    }

}