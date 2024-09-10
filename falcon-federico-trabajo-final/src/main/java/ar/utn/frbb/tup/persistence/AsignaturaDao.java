package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;

public interface AsignaturaDao {
    Asignatura getAsignaturaById(Integer idAsignatura);
}