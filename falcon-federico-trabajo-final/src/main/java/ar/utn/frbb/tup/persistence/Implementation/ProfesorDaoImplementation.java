package ar.utn.frbb.tup.persistence.Implementation;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.ProfesorDao;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProfesorDaoImplementation implements ProfesorDao {
    static final Map<Integer, Profesor> repoProfesores = new HashMap<>();
    @Override
    public Profesor crearProfesor(Profesor profesor) throws ProfesorAlreadyExistsException {
        if (repoProfesores.containsKey(profesor.getId())) {
            throw new ProfesorAlreadyExistsException("El id ingresado ya existe en la base de datos");
        }
        repoProfesores.put(profesor.getId(), profesor);
        return profesor;
    }
}
