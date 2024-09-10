package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.dto.AlumnoDTO;
import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;

import java.util.Map;

public interface AlumnoService {
    Alumno crearAlumno(AlumnoDTO alumnoDTO) throws AlumnoAlreadyExistsException;

    Alumno modificarAlumno(Integer idAlumno, Map<String, Object> campos) throws AlumnoNotFoundException;

    String eliminarAlumno(Integer idAlumno) throws AlumnoNotFoundException;

    Alumno cambiarEstadoAsignatura(Integer idAlumno, Integer idAsignatura, AsignaturaDTO nuevoEstado) throws AlumnoNotFoundException;
}
