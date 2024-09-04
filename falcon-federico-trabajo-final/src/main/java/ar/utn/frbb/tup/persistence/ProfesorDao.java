package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;

public interface ProfesorDao {
    Profesor crearProfesor(Profesor profesor) throws ProfesorAlreadyExistsException;
}
