package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.MateriaNotFoundException;


public interface MateriaService {
    Materia crearMateria(MateriaDTO materiaDTO) throws MateriaAlreadyExistsException;

    String eliminarMateria(Integer idMateria) throws MateriaNotFoundException;

    Materia getMateriaById(Integer idMateria);

    Materia setProfesor(Integer idMateria, Integer idProfesor);
}
