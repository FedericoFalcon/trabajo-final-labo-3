package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;

public interface MateriaDao {
    Materia crearMateria(Materia materia) throws MateriaAlreadyExistsException;

    String deleteMateria(Integer idMateria) throws MateriaNotFoundException;

    Materia getMateriaById(Integer idMateria);
}
