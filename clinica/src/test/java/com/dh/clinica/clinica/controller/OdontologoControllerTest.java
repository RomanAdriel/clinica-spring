package com.dh.clinica.clinica.controller;

import com.dh.clinica.clinica.exceptions.BadRequestException;
import com.dh.clinica.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.clinica.model.dto.OdontologoDto;
import com.dh.clinica.clinica.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class OdontologoControllerTest {

    @InjectMocks
    OdontologoController odontologoController;

    @Mock
    OdontologoService odontologoService;

    @Test
    void buscar_odontologo_por_id_existente() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre("Pepito");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);

        ResponseEntity<OdontologoDto> entidadEsperada = ResponseEntity.ok(odontologoDto);


        //ACT

        when(odontologoService.buscarPorId(any())).thenReturn(odontologoDto);

        //ASSERT

        Assertions.assertEquals(entidadEsperada, odontologoController.buscarOdontologoPorId(1L));
    }

    @Test
    void buscar_odontologo_por_id_no_existente() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre("Pepito");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);

        //ACT

        when(odontologoService.buscarPorId(any())).thenThrow(ResourceNotFoundException.class);

        //ASSERT

        Assertions.assertThrows(ResourceNotFoundException.class,
                                () -> {odontologoController.buscarOdontologoPorId(1L);});


    }

    @Test
    void buscar_odontologo_por_id_menor_a_1() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre("Pepito");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);

        //ACT

        when(odontologoService.buscarPorId(any())).thenThrow(BadRequestException.class);

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {odontologoController.buscarOdontologoPorId(-1L);});


    }

    @Test
    void buscar_odontologo_por_id_nulo() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre("Pepito");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);

        //ACT

        when(odontologoService.buscarPorId(any())).thenThrow(BadRequestException.class);

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {odontologoController.buscarOdontologoPorId(null);});


    }


}