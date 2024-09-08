package ar.utn.frbb.tup.persistence.Implementation;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.ProfesorDao;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProfesorDaoImplementation implements ProfesorDao {
    static final Map<Integer, Profesor> repoProfesores = new HashMap<>();
    @Override
    public Profesor crearProfesor(Profesor profesor) throws ProfesorAlreadyExistsException {
        if (repoProfesores.containsKey(profesor.getId())) {
            throw new ProfesorAlreadyExistsException("No se puede crear. El profesor ya existe.");
        }
        System.out.println(profesor);
        repoProfesores.put(profesor.getId(), profesor);
        return profesor;
    }


    @Override
    public Profesor getProfesorById(Integer idProfesor) throws ProfesorNotFoundException {
        if (repoProfesores.containsKey(idProfesor)) {
            return repoProfesores.get(idProfesor);
        }else{
            throw new ProfesorNotFoundException("El id ingresado no corresponde a ningun profesor");
        }
    }

    @Override
    public String deleteProfesor(Integer idProfesor) throws ProfesorNotFoundException {
        if (idProfesor == null || !(repoProfesores.containsKey(idProfesor))) {
            throw new ProfesorNotFoundException("Error. El profesor no existe.");
        }
        repoProfesores.remove(idProfesor);
        return ("Profesor eliminado con exito");
    }
}
