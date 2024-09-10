package ar.utn.frbb.tup.persistence.implementation;

import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Asignatura;
// import ar.utn.frbb.tup.persistence.AlumnoDao;
import ar.utn.frbb.tup.persistence.AsignaturaDao;
import ar.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AsignaturaDaoImplementation implements AsignaturaDao {
    static final Map<Integer, Asignatura> repoAsignaturas = new HashMap<>();

    @Override
    public Asignatura getAsignaturaById(Integer idAsignatura) {
        return repoAsignaturas.get(idAsignatura);
    }
}
