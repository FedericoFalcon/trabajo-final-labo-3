package ar.utn.frbb.tup.persistence.implementation;

import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.persistence.AlumnoDao;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AlumnoDaoImplementation implements AlumnoDao {
    static final Map<Integer, Alumno> repositorioAlumnos = new HashMap<>();
    @Override
    public Alumno createAlumno(Alumno a) throws AlumnoAlreadyExistsException {
        if (repositorioAlumnos.containsKey(a.getId())) {
            throw new AlumnoAlreadyExistsException("El alumno ya existe en la base de datos");
        }
        repositorioAlumnos.put(a.getId(), a);
        return a;
    }

    @Override
    public Alumno getAlumnoById(Integer idAlumno) throws AlumnoNotFoundException {
        if (!repositorioAlumnos.containsKey(idAlumno)) {
            throw new AlumnoNotFoundException("El id del alumno no existe");
        }
        return repositorioAlumnos.get(idAlumno);
    }

    @Override
    public String deleteAlumno(Integer idAlumno) throws AlumnoNotFoundException {
        if (!repositorioAlumnos.containsKey(idAlumno)) {
            throw new AlumnoNotFoundException("El id ingresado no pertenece a ningun alumno");
        }
        repositorioAlumnos.remove(idAlumno);
        return ("Alumno eliminado con exito");
    }
}
