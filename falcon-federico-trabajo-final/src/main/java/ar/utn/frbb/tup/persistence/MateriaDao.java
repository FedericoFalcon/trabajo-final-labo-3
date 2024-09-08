package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;

public interface MateriaDao {
    Materia crearMateria(Materia materia) throws MateriaAlreadyExistsException;
}
