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
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) {

        ResponseEntity<PacienteDto> pacienteDto = null;

        try {

            pacienteDto = ResponseEntity.ok(pacienteService.buscarPorId(id));

            if (!pacienteDto.hasBody()) {
                pacienteDto = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }


        } catch (Exception e) {

            pacienteDto = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }


        return pacienteDto;

    }

    @PostMapping
    public ResponseEntity<PacienteDto> guardarPaciente(@RequestBody Paciente paciente) {

        ResponseEntity<PacienteDto> pacienteDto = null;

        try {


            pacienteDto = ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                               .buildAndExpand(pacienteService.guardar(paciente)).toUri()).build();


        } catch (Exception e) {

            pacienteDto = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

        return pacienteDto;

    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> modificarPaciente(@RequestBody Paciente paciente, @PathVariable Long id) {

        PacienteDto pacienteActualizado = pacienteService.actualizar(paciente, id);
        ResponseEntity<PacienteDto> pacienteDto = null;

        try {

            if (pacienteActualizado != null) {

                pacienteDto = ResponseEntity.ok(pacienteActualizado);

            } else {

                pacienteDto = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        } catch (Exception e) {

            pacienteDto = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

        return pacienteDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPaciente(@PathVariable Long id) throws ResourceNotFoundException, BadRequestException {

            pacienteService.borrarPorId(id);

            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDto>> buscarPacientes() {

        ResponseEntity<List<PacienteDto>> listaPacientes = null;

        try {
            listaPacientes = ResponseEntity.ok(pacienteService.buscarTodos());
        } catch (Exception e) {

            listaPacientes = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return listaPacientes;

    }


}
