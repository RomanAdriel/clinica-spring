package com.dh.clinica.clinica.controller;


import com.dh.clinica.clinica.exceptions.BadRequestException;
import com.dh.clinica.clinica.exceptions.ResourceNotFoundException;
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
    public ResponseEntity<TurnoDto> guardarTurno(@RequestBody Turno turno) throws BadRequestException {

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                           .buildAndExpand(turnoService.guardar(turno)).toUri()).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<TurnoDto> actualizarTurno(@RequestBody Turno turno, @PathVariable Long id) throws BadRequestException, ResourceNotFoundException {

        return ResponseEntity.ok(
                turnoService.actualizarTurno(turno, id));

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

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id) throws BadRequestException {

        return ResponseEntity.ok(turnoService.buscarPorId(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarTurno(@PathVariable Long id) throws ResourceNotFoundException, BadRequestException {

        turnoService.borrarTurno(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/paciente/{dni}")
    public ResponseEntity<List<TurnoDto>> buscarTurnosPorPaciente(@PathVariable int dni) throws BadRequestException {

        return ResponseEntity.ok(turnoService.buscarTurnosPorPaciente(dni));

    }

    @GetMapping("/odontologo/{matricula}")
    public ResponseEntity<List<TurnoDto>> buscarTurnosPorOdontologo(@PathVariable int matricula) throws BadRequestException {

        return ResponseEntity.ok(turnoService.buscarTurnosPorOdontologo(matricula));

    }
}
