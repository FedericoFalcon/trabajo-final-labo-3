package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;

public interface ProfesorDao {
    Profesor crearProfesor(Profesor profesor) throws ProfesorAlreadyExistsException;

    Profesor getProfesorById(Integer idProfesor) throws ProfesorNotFoundException;

    String deleteProfesor(Integer idProfesor) throws ProfesorNotFoundException;
}
