package com.dh.clinica.clinica.controller;

import com.dh.clinica.clinica.exceptions.BadRequestException;
import com.dh.clinica.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.clinica.model.Odontologo;
import com.dh.clinica.clinica.model.dto.OdontologoDto;
import com.dh.clinica.clinica.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

        odontologoDto.setNombre("Juan");
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

        odontologoDto.setNombre("Juan");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);

        //ACT

        when(odontologoService.buscarPorId(any())).thenThrow(ResourceNotFoundException.class);

        //ASSERT

        Assertions.assertThrows(ResourceNotFoundException.class,
                                () -> {
                                    odontologoController.buscarOdontologoPorId(1L);
                                });


    }

    @Test
    void buscar_odontologo_por_id_menor_a_1() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre("Juan");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);

        //ACT

        when(odontologoService.buscarPorId(any())).thenThrow(BadRequestException.class);

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoController.buscarOdontologoPorId(-1L);
                                });


    }

    @Test
    void buscar_odontologo_por_id_nulo() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre("Juan");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);

        //ACT

        when(odontologoService.buscarPorId(any())).thenThrow(BadRequestException.class);

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoController.buscarOdontologoPorId(null);
                                });


    }

    @Test
    void guardar_odontologo_con_datos_validos() throws BadRequestException {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre("Juan");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);


        //ACT

        when(odontologoService.guardar(any())).thenReturn(odontologoDto);
        ResponseEntity<OdontologoDto> entidadEsperada = ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                           .buildAndExpand(
                                                   odontologoService.guardar(odontologo))
                                           .toUri()).build();

        //ASSERT

        Assertions.assertEquals(entidadEsperada, odontologoController.guardarOdontologo(odontologo));


    }

    @Test
    void guardar_odontologo_con_datos_invalidos() throws BadRequestException {

        //ARRANGE

        Odontologo odontologo = new Odontologo();
        odontologo.setApellido("Perez");

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre("Juan");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);


        //ACT

        when(odontologoService.guardar(any())).thenThrow(BadRequestException.class);

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoController.guardarOdontologo(odontologo);
                                });


    }

    @Test
    void actualizar_odontologo_con_datos_validos() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre("Juan");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);


        //ACT

        when(odontologoService.actualizar(any(), any())).thenReturn(odontologoDto);
        ResponseEntity<OdontologoDto> entidadEsperada = ResponseEntity.ok(
                odontologoService.actualizar(odontologo, 1L));

        //ASSERT

        Assertions.assertEquals(entidadEsperada, odontologoController.modificarOdontologo(odontologo, 1L));


    }

    @Test
    void actualizar_odontologo_con_body_invalido() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        Odontologo odontologo = new Odontologo();
        odontologo.setApellido("Perez");

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre("Juan");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);


        //ACT

        when(odontologoService.actualizar(any(), any())).thenThrow(BadRequestException.class);

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoController.modificarOdontologo(odontologo, 1L);
                                });


    }

    @Test
    void actualizar_odontologo_con_id_invalido() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        Odontologo odontologo = new Odontologo();
        odontologo.setApellido("Perez");
        odontologo.setId(-1L);

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre("Juan");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);


        //ACT

        when(odontologoService.actualizar(any(), any())).thenThrow(BadRequestException.class);

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoController.modificarOdontologo(odontologo, -1L);
                                });


    }

    @Test
    void actualizar_odontologo_inexistente() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        Odontologo odontologo = new Odontologo(5L, "Juan", "Perez", 12345);

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre("Juan");
        odontologoDto.setApellido("Perez");
        odontologoDto.setMatricula(12345);


        //ACT

        when(odontologoService.actualizar(any(), any())).thenThrow(ResourceNotFoundException.class);

        //ASSERT

        Assertions.assertThrows(ResourceNotFoundException.class,
                                () -> {
                                    odontologoController.modificarOdontologo(odontologo, 5L);
                                });


    }

    @Test
    void borrar_odontologo_existente() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        ResponseEntity<Void> entidadEsperada = new ResponseEntity<>(HttpStatus.OK);
        //ACT

        doNothing().when(odontologoService).borrarPorId(any());

        //ASSERT

        Assertions.assertEquals(entidadEsperada, odontologoController.borrarOdontologo(1L));


    }

    @Test
    void borrar_odontologo_con_id_null() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        //ACT

        doThrow(BadRequestException.class).when(odontologoService).borrarPorId(any());

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoController.borrarOdontologo(null);
                                });


    }

    @Test
    void borrar_odontologo_con_id_negativo() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        //ACT

        doThrow(BadRequestException.class).when(odontologoService).borrarPorId(any());

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoController.borrarOdontologo(-1L);
                                });


    }

    @Test
    void borrar_odontologo_inexistente() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        //ACT

        doThrow(ResourceNotFoundException.class).when(odontologoService).borrarPorId(any());

        //ASSERT

        Assertions.assertThrows(ResourceNotFoundException.class,
                                () -> {
                                    odontologoController.borrarOdontologo(25L);
                                });

    }

    @Test
    void listar_odontologos() {

        //ARRANGE

        List<OdontologoDto> listaOdontologosDto = new ArrayList<>();

        Odontologo odontologo1 = new Odontologo(1L, "Juan", "Perez", 12345);
        Odontologo odontologo2 = new Odontologo(2L, "Pedro", "Martínez", 23456);

        OdontologoDto odontologoDto1 = new OdontologoDto();
        OdontologoDto odontologoDto2 = new OdontologoDto();

        odontologoDto1.setNombre(odontologo1.getNombre());
        odontologoDto1.setApellido(odontologo1.getApellido());
        odontologoDto1.setMatricula(odontologo1.getMatricula());

        odontologoDto2.setNombre(odontologo2.getNombre());
        odontologoDto2.setApellido(odontologo2.getApellido());
        odontologoDto2.setMatricula(odontologo2.getMatricula());

        listaOdontologosDto.add(odontologoDto1);
        listaOdontologosDto.add(odontologoDto2);

        //ACT

        when(odontologoService.buscarTodos()).thenReturn(listaOdontologosDto);
        ResponseEntity<List<OdontologoDto>> entidadEsperada = ResponseEntity.ok(odontologoService.buscarTodos());

        //ASSERT

        Assertions.assertEquals(entidadEsperada, odontologoController.buscarOdontologos());

    }

}