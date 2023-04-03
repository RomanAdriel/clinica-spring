package com.dh.clinica.clinica.controller;


import com.dh.clinica.clinica.model.Odontologo;
import com.dh.clinica.clinica.model.dto.OdontologoDto;
import com.dh.clinica.clinica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;


    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) {

        ResponseEntity<OdontologoDto> odontologoDto = null;

        try {

            odontologoDto = ResponseEntity.ok(odontologoService.buscarPorId(id));

            if (!odontologoDto.hasBody()) {
                odontologoDto = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }


        } catch (Exception e) {

            odontologoDto = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }


        return odontologoDto;

    }

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) {

        ResponseEntity<Odontologo> odontologoEntity = null;

        try {


            odontologoEntity = ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                               .buildAndExpand(odontologoService.guardar(odontologo)).toUri()).build();


        } catch (Exception e) {

            odontologoEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

        return odontologoEntity;

    }


    @PutMapping("/{id}")
    public ResponseEntity<OdontologoDto> modificarOdontologo(@RequestBody Odontologo odontologo,
                                                             @PathVariable Long id) {

        OdontologoDto odontologoActualizado = odontologoService.actualizar(odontologo, id);
        ResponseEntity<OdontologoDto> odontologoDto = null;

        try {

            if (odontologoActualizado != null) {

                odontologoDto = ResponseEntity.ok(odontologoActualizado);

            } else {

                odontologoDto = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        } catch (Exception e) {

            odontologoDto = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

        return odontologoDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarOdontologo(@PathVariable Long id) {

        try {
            odontologoService.borrarPorId(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping
    public ResponseEntity<List<OdontologoDto>> buscarOdontologos() {

        ResponseEntity<List<OdontologoDto>> listaOdontologos = null;

        try {
            listaOdontologos = ResponseEntity.ok(odontologoService.buscarTodos());
        } catch (Exception e) {

            listaOdontologos = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return listaOdontologos;

    }
}
