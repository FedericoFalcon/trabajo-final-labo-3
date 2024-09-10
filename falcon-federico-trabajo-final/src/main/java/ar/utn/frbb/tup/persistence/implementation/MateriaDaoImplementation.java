package ar.utn.frbb.tup.persistence.implementation;

import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.MateriaDao;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MateriaDaoImplementation implements MateriaDao {
    static final Map<Integer, Materia> repoMaterias = new HashMap<>();
    @Override
    public Materia crearMateria(Materia materia) throws MateriaAlreadyExistsException {
        if (repoMaterias.containsKey(materia.getId())) {
            throw new MateriaAlreadyExistsException("No se puede crear. La materia ya existe.");
        }
        repoMaterias.put(materia.getId(), materia);
        return materia;
    }

    @Override
    public String deleteMateria(Integer idMateria) throws MateriaNotFoundException {
        if (idMateria == null || !(repoMaterias.containsKey(idMateria))) {
            throw new MateriaNotFoundException("Error. La materia no existe.");
        }
        repoMaterias.remove(idMateria);
        return ("Materia eliminada con exito");
    }

    @Override
    public Materia getMateriaById(Integer idMateria) {
        if (repoMaterias.containsKey(idMateria)) {
            return repoMaterias.get(idMateria);
        }
        return null;
    }
}
