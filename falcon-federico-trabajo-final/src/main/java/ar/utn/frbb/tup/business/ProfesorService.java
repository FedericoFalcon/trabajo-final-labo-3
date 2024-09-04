package ar.utn.frbb.tup.business;
import ar.utn.frbb.tup.dto.ProfesorDTO;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;

public interface ProfesorService {
    Profesor crearProfesor(ProfesorDTO profesorDTO) throws ProfesorAlreadyExistsException;
}
