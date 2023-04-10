package com.dh.clinica.clinica.service;

import com.dh.clinica.clinica.exceptions.BadRequestException;
import com.dh.clinica.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.clinica.model.Odontologo;
import com.dh.clinica.clinica.model.dto.OdontologoDto;
import com.dh.clinica.clinica.repository.OdontologoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class OdontologoServiceTest {

    @InjectMocks
    OdontologoService odontologoService;

    @Mock
    OdontologoRepository odontologoRepository;

    @Test
    void buscar_odontologo_existente_por_id() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre(odontologo.getNombre());
        odontologoDto.setApellido(odontologo.getApellido());
        odontologoDto.setMatricula(odontologo.getMatricula());

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.of(odontologo));

        //ASSERT

        Assertions.assertEquals(odontologoDto.getNombre(), odontologoService.buscarPorId(1L).getNombre());
        Assertions.assertEquals(odontologoDto.getApellido(), odontologoService.buscarPorId(1L).getApellido());
        Assertions.assertEquals(odontologoDto.getMatricula(), odontologoService.buscarPorId(1L).getMatricula());

    }

    @Test
    void buscar_odontologo_inexistente() {

        //ARRANGE

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.empty());

        //ASSERT

        Assertions.assertThrows(ResourceNotFoundException.class,
                                () -> {
                                    odontologoService.buscarPorId(1L);
                                });

    }

    @Test
    void buscar_odontologo_con_id_nulo() {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.of(odontologo));

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoService.buscarPorId(null);
                                });

    }

    @Test
    void buscar_odontologo_con_id_negativo() {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.of(odontologo));

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoService.buscarPorId(-1L);
                                });

    }

    @Test
    void guardar_odontologo_con_datos_validos() throws BadRequestException {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre(odontologo.getNombre());
        odontologoDto.setApellido(odontologo.getApellido());
        odontologoDto.setMatricula(odontologo.getMatricula());

        //ACT

        when(odontologoRepository.save(any())).thenReturn(odontologo);

        //ASSERT

        Assertions.assertEquals(odontologoDto.getNombre(), odontologoService.guardar(odontologo).getNombre());
        Assertions.assertEquals(odontologoDto.getApellido(), odontologoService.guardar(odontologo).getApellido());
        Assertions.assertEquals(odontologoDto.getMatricula(), odontologoService.guardar(odontologo).getMatricula());

    }

    @Test
    void guardar_odontologo_con_body_incorrecto() {

        //ARRANGE

        Odontologo odontologo = new Odontologo();

        odontologo.setApellido("Test");

        //ACT

        when(odontologoRepository.save(any())).thenReturn(odontologo);

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoService.guardar(odontologo);
                                });

    }

    @Test
    void actualizar_odontologo_con_datos_validos() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre(odontologo.getNombre());
        odontologoDto.setApellido(odontologo.getApellido());
        odontologoDto.setMatricula(odontologo.getMatricula());

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.of(odontologo));
        when(odontologoRepository.save(any())).thenReturn(odontologo);

        //ASSERT

        Assertions.assertEquals(odontologoDto.getNombre(), odontologoService.actualizar(odontologo, 1L).getNombre());
        Assertions.assertEquals(odontologoDto.getApellido(),
                                odontologoService.actualizar(odontologo, 1L).getApellido());
        Assertions.assertEquals(odontologoDto.getMatricula(),
                                odontologoService.actualizar(odontologo, 1L).getMatricula());

    }

    @Test
    void actualizar_odontologo_inexistente() {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.empty());
        when(odontologoRepository.save(any())).thenReturn(odontologo);

        //ASSERT

        Assertions.assertThrows(ResourceNotFoundException.class,
                                () -> {
                                    odontologoService.actualizar(odontologo, odontologo.getId());
                                });

    }

    @Test
    void actualizar_odontologo_con_body_incorrecto() {

        //ARRANGE

        Odontologo odontologo = new Odontologo();

        odontologo.setApellido("Test");

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.of(odontologo));
        when(odontologoRepository.save(any())).thenReturn(odontologo);

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoService.actualizar(odontologo, 1L);
                                });

    }

    @Test
    void actualizar_odontologo_con_id_nulo() {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.of(odontologo));
        when(odontologoRepository.save(any())).thenReturn(odontologo);

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoService.actualizar(odontologo, null);
                                });

    }

    @Test
    void actualizar_odontologo_con_id_negativo() {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.of(odontologo));
        when(odontologoRepository.save(any())).thenReturn(odontologo);

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoService.actualizar(odontologo, -1L);
                                });

    }


    @Test
    void listar_odontologos() {

        //ARRANGE
        List<Odontologo> listaOdontologos = new ArrayList<>();
        List<OdontologoDto> listaOdontologosEsperada = new ArrayList<>();

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

        listaOdontologos.add(odontologo1);
        listaOdontologos.add(odontologo2);

        listaOdontologosEsperada.add(odontologoDto1);
        listaOdontologosEsperada.add(odontologoDto2);

        //ACT

        when(odontologoRepository.findAll()).thenReturn(listaOdontologos);

        //ASSERT

        Assertions.assertEquals(listaOdontologosEsperada.size(), odontologoService.buscarTodos().size());
    }

    @Test
    void borrar_odontologo_existente() {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.of(odontologo));
        doNothing().when(odontologoRepository).deleteById(any());

        //ASSERT

        Assertions.assertDoesNotThrow(() -> odontologoService.borrarPorId(odontologo.getId()));
    }

    @Test
    void borrar_odontologo_inexistente() {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.empty());
        doNothing().when(odontologoRepository).deleteById(any());

        //ASSERT

        Assertions.assertThrows(ResourceNotFoundException.class,
                                () -> odontologoService.borrarPorId(odontologo.getId()));
    }

    @Test
    void borrar_odontologo_con_id_nulo() {

        //ARRANGE

        Odontologo odontologo = new Odontologo(null, "Juan", "Perez", 12345);

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.of(odontologo));
        doNothing().when(odontologoRepository).deleteById(any());

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> odontologoService.borrarPorId(odontologo.getId()));
    }

    @Test
    void borrar_odontologo_con_id_negativo() {

        //ARRANGE

        Odontologo odontologo = new Odontologo(-1L, "Juan", "Perez", 12345);

        //ACT

        when(odontologoRepository.findById(any())).thenReturn(Optional.of(odontologo));
        doNothing().when(odontologoRepository).deleteById(any());

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> odontologoService.borrarPorId(odontologo.getId()));
    }


    @Test
    void buscar_odontologo_existente_por_matricula() throws BadRequestException, ResourceNotFoundException {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setNombre(odontologo.getNombre());
        odontologoDto.setApellido(odontologo.getApellido());
        odontologoDto.setMatricula(odontologo.getMatricula());

        //ACT

        when(odontologoRepository.findByMatricula(anyInt())).thenReturn(Optional.of(odontologo));

        //ASSERT

        Assertions.assertEquals(odontologoDto.getNombre(),
                                odontologoService.buscarPorMatricula(odontologo.getMatricula()).getNombre());
        Assertions.assertEquals(odontologoDto.getApellido(), odontologoService.buscarPorMatricula(odontologo.getMatricula()).getApellido());
        Assertions.assertEquals(odontologoDto.getMatricula(), odontologoService.buscarPorMatricula(odontologo.getMatricula()).getMatricula());

    }

    @Test
    void buscar_odontologo_por_matricula_no_existente() throws BadRequestException {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 12345);

        //ACT

        when(odontologoRepository.findByMatricula(anyInt())).thenReturn(Optional.empty());

        //ASSERT

        Assertions.assertNull(odontologoService.buscarPorMatricula(odontologo.getMatricula()));


    }

    @Test
    void buscar_odontologo_por_matricula_nula() {

        //ARRANGE

        Odontologo odontologo = new Odontologo();
        odontologo.setId(1L);
        odontologo.setNombre("Dentista");
        odontologo.setApellido("Dentista");

        //ACT

        when(odontologoRepository.findByMatricula(anyInt())).thenReturn(Optional.of(odontologo));

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoService.buscarPorMatricula(odontologo.getMatricula());
                                });
    }

    @Test
    void buscar_odontologo_por_matricula_negativa() {

        //ARRANGE

        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", -12345);

        //ACT

        when(odontologoRepository.findByMatricula(anyInt())).thenReturn(Optional.of(odontologo));

        //ASSERT

        Assertions.assertThrows(BadRequestException.class,
                                () -> {
                                    odontologoService.buscarPorMatricula(odontologo.getMatricula());
                                });
    }

}