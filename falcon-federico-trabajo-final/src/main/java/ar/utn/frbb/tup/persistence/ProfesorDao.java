package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;

import java.util.List;

public interface ProfesorDao {
    Profesor crearProfesor(Profesor profesor) throws ProfesorAlreadyExistsException, MateriaNotFoundException;

    Profesor getProfesorById(Integer idProfesor) throws ProfesorNotFoundException;

    String deleteProfesor(Integer idProfesor) throws ProfesorNotFoundException;

    List<Materia> getMateriasProfesor (Integer idProfesor) throws ProfesorNotFoundException, MateriaNotFoundException;
}
