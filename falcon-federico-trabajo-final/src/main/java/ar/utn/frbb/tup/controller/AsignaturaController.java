package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.AlumnoService;
import ar.utn.frbb.tup.business.AsignaturaService;
import ar.utn.frbb.tup.dto.AlumnoDTO;
import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("asignatura")
public class AsignaturaController {
    @Autowired
    AsignaturaService asignaturaService;
    @PostMapping
    public Asignatura crearAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {
        return asignaturaService.crearAsignatura(asignaturaDTO);
    }
}
