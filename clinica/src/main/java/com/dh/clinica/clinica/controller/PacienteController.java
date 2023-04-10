package com.dh.clinica.clinica.controller;

import com.dh.clinica.clinica.exceptions.BadRequestException;
import com.dh.clinica.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.clinica.model.Paciente;
import com.dh.clinica.clinica.model.dto.PacienteDto;
import com.dh.clinica.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) throws BadRequestException, ResourceNotFoundException {

        return ResponseEntity.ok(pacienteService.buscarPorId(id));

    }

    @PostMapping
    public ResponseEntity<PacienteDto> guardarPaciente(@RequestBody Paciente paciente) throws BadRequestException {

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                           .buildAndExpand(pacienteService.guardar(paciente)).toUri()).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> modificarPaciente(@RequestBody Paciente paciente, @PathVariable Long id) throws BadRequestException {

        return ResponseEntity.ok(pacienteService.actualizar(paciente, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPaciente(@PathVariable Long id) throws ResourceNotFoundException, BadRequestException {

            pacienteService.borrarPorId(id);

            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDto>> buscarPacientes() {

        ResponseEntity<List<PacienteDto>> listaPacientes = null;

        listaPacientes = ResponseEntity.ok(pacienteService.buscarTodos());

        return listaPacientes;

    }


}
