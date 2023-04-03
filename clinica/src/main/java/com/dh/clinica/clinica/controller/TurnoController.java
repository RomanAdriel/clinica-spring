package com.dh.clinica.clinica.controller;


import com.dh.clinica.clinica.model.Turno;
import com.dh.clinica.clinica.model.dto.TurnoDto;
import com.dh.clinica.clinica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<TurnoDto> guardarTurno(@RequestBody Turno turno) {

        ResponseEntity<TurnoDto> turnoDto = null;

        try {


            turnoDto = ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                               .buildAndExpand(turnoService.guardar(turno)).toUri()).build();


        } catch (Exception e) {

            turnoDto = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

        return turnoDto;

    }

    @GetMapping
    public ResponseEntity<List<TurnoDto>> buscarTurnos() {

        ResponseEntity<List<TurnoDto>> listaTurnos = null;

        try {
            listaTurnos = ResponseEntity.ok(turnoService.buscarTodos());
        } catch (Exception e) {

            listaTurnos = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return listaTurnos;

    }
}
