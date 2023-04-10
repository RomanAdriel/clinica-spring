package com.dh.clinica.clinica.controller;

import com.dh.clinica.clinica.exceptions.BadRequestException;
import com.dh.clinica.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.clinica.model.dto.DomicilioDto;
import com.dh.clinica.clinica.model.dto.PacienteDto;
import com.dh.clinica.clinica.service.DomicilioService;
import com.dh.clinica.clinica.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
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
    void buscar_paciente_por_id_no_existente() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        PacienteDto pacienteDto = new PacienteDto();

        pacienteDto.setNombre("Juan");
        pacienteDto.setApellido("Perez");

        //ACT

        when(pacienteService.buscarPorId(any())).thenThrow(ResourceNotFoundException.class);

        //ASSERT

        Assertions.assertThrows(ResourceNotFoundException.class,
                                () -> {
                                    pacienteController.buscarPacientePorId(1L);
                                });
    }


    @Test
    void buscar_paciente_por_id_existente() throws BadRequestException, ResourceNotFoundException {

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