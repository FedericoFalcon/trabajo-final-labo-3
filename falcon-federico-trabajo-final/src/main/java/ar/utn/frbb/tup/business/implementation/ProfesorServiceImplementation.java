package ar.utn.frbb.tup.business.implementation;
import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.business.ProfesorService;
import ar.utn.frbb.tup.dto.ProfesorDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.ProfesorDao;
import ar.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Comparator;

@Service
public class ProfesorServiceImplementation implements ProfesorService {

    @Autowired
    ProfesorDao profesorDao;

    @Override
    public Profesor crearProfesor(ProfesorDTO profesorDTO) throws ProfesorAlreadyExistsException, MateriaNotFoundException {
        Profesor profesor = new Profesor();

        profesor.setId(profesorDTO.getId());
        profesor.setNombre(profesorDTO.getNombre());
        profesor.setApellido(profesorDTO.getApellido());
        profesor.setTitulo(profesorDTO.getTitulo());
        profesor.setMateriasDictadas(profesorDTO.getMateriasDictadas());

        return this.profesorDao.crearProfesor(profesor);
    }

    @Override
    public Profesor modificarProfesor(Integer idProfesor, Map<String, Object> atributos) throws ProfesorNotFoundException {
        Profesor profesor = profesorDao.getProfesorById(idProfesor);

        for (Map.Entry<String, Object> atributo : atributos.entrySet()) {
            String nombreAtributo = atributo.getKey();
            Object valor = atributo.getValue();
            modificarAtributos(nombreAtributo, valor, profesor);
        }

        return profesor;
    }

    @Override
    public String eliminarProfesor(Integer idProfesor) throws ProfesorNotFoundException {
        return profesorDao.deleteProfesor(idProfesor);
    }

    private void modificarAtributos(String nombreAtributo, Object value, Profesor profesor) {
        switch (nombreAtributo){
            case "nombre" -> {
                if (value instanceof String) {
                    profesor.setNombre((String) value);
                }
            }
            case "apellido" -> {
                if (value instanceof String) {
                    profesor.setApellido((String) value);
                }
            }
            case "titulo" -> {
                if (value instanceof String) {
                    profesor.setTitulo((String) value);
                }
            }
        }
    }

    @Override
    public List<Materia> getMateriasProfesor(Integer idProfesor) throws ProfesorNotFoundException, MateriaNotFoundException {
        List<Materia> listaMaterias = profesorDao.getMateriasProfesor(idProfesor);
        listaMaterias.sort(Comparator.comparing(Materia::getNombre));

        return listaMaterias;
    }

}
