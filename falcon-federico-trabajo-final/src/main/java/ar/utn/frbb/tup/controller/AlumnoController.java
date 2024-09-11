package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.AlumnoService;
import ar.utn.frbb.tup.dto.AlumnoDTO;
import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import ar.utn.frbb.tup.persistence.exception.CorrelatividadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("alumno")
public class AlumnoController {
    @Autowired
    AlumnoService alumnoService;
    @PostMapping
    public Alumno crearAlumno(@RequestBody AlumnoDTO alumnoDTO) throws AlumnoAlreadyExistsException {
        return alumnoService.crearAlumno(alumnoDTO);
    }

    @PutMapping("/{idAlumno}")
    public Alumno modificarAlumno(@PathVariable Integer idAlumno, @RequestBody Map<String,Object> campos) throws AlumnoNotFoundException {
        return alumnoService.modificarAlumno(idAlumno, campos);
    }

    @DeleteMapping("/{idAlumno}")
    public String eliminarAlumno(@PathVariable Integer idAlumno) throws AlumnoNotFoundException{
        return alumnoService.eliminarAlumno(idAlumno);
    }

    @PutMapping("/{idAlumno}/asignatura/{idAsignatura}")
    public Alumno cambiarEstadoAsignatura(
            @PathVariable Integer idAlumno,
            @PathVariable Integer idAsignatura,
            @RequestBody AsignaturaDTO nuevoEstado) throws AlumnoNotFoundException, CorrelatividadException {
        return alumnoService.cambiarEstadoAsignatura(idAlumno, idAsignatura, nuevoEstado);
    }
}
