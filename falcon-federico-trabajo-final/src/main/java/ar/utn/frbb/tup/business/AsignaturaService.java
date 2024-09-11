package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.dto.AlumnoDTO;
import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;

import java.util.List;

public interface AsignaturaService {
    Asignatura crearAsignatura(AsignaturaDTO asignaturaDTO);
}
