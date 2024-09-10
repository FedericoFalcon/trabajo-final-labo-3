package ar.utn.frbb.tup.persistence.implementation;
import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.business.ProfesorService;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.ProfesorDao;
import ar.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProfesorDaoImplementation implements ProfesorDao {

    @Autowired
    MateriaService materiaService;

    static final Map<Integer, Profesor> repoProfesores = new HashMap<>();
    @Override
    public Profesor crearProfesor(Profesor profesor) throws ProfesorAlreadyExistsException, MateriaNotFoundException {
        if (repoProfesores.containsKey(profesor.getId())) {
            throw new ProfesorAlreadyExistsException("No se puede crear. El profesor ya existe.");
        }

        for (Integer idMateria: profesor.getMateriasDictadas()) {
            Materia m = materiaService.getMateriaById(idMateria); // Podria ser un metodo en materia service que devuelva un booleano
            if (m == null){
                throw new MateriaNotFoundException("Error. La materia no existe.");
            }
            materiaService.setProfesor(idMateria, profesor.getId());
        }
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

    @Override
    public List<Materia> getMateriasProfesor(Integer idProfesor) throws ProfesorNotFoundException, MateriaNotFoundException {
        if (idProfesor == null || !(repoProfesores.containsKey(idProfesor))) {
            throw new ProfesorNotFoundException("Error. El profesor no existe.");
        }

        List<Materia> listaMaterias = new ArrayList<>();

        Profesor p = repoProfesores.get(idProfesor);

        for (Integer idMateria: p.getMateriasDictadas()) {
            Materia m = materiaService.getMateriaById(idMateria);
            if (m == null){
                throw new MateriaNotFoundException("Error. La materia no existe.");
            }
            listaMaterias.add(m);
        }
       return listaMaterias;
    }
}
