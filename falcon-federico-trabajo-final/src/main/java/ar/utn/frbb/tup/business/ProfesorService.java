package ar.utn.frbb.tup.business;
import ar.utn.frbb.tup.dto.ProfesorDTO;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;

import java.util.List;
import java.util.Map;

public interface ProfesorService {
    Profesor crearProfesor(ProfesorDTO profesorDTO) throws ProfesorAlreadyExistsException;

    Profesor modificarProfesor(Integer idProfesor, Map<String, Object> campos) throws ProfesorNotFoundException;

    String eliminarProfesor(Integer idProfesor) throws ProfesorNotFoundException;
}
