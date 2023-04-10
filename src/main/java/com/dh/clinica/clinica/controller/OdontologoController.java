package com.dh.clinica.clinica.controller;


import com.dh.clinica.clinica.exceptions.BadRequestException;
import com.dh.clinica.clinica.exceptions.ResourceNotFoundException;
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
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) throws BadRequestException, ResourceNotFoundException {

        return ResponseEntity.ok(odontologoService.buscarPorId(id));

    }

    @PostMapping
    public ResponseEntity<OdontologoDto> guardarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                           .buildAndExpand(odontologoService.guardar(odontologo)).toUri()).build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<OdontologoDto> modificarOdontologo(@RequestBody Odontologo odontologo,
                                                             @PathVariable Long id) throws BadRequestException, ResourceNotFoundException {

        return ResponseEntity.ok(
                odontologoService.actualizar(odontologo, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarOdontologo(@PathVariable Long id) throws ResourceNotFoundException, BadRequestException {

        odontologoService.borrarPorId(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<OdontologoDto>> buscarOdontologos() {

        return ResponseEntity.ok(odontologoService.buscarTodos());

    }
}
