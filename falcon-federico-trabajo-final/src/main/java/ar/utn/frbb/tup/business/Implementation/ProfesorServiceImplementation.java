package ar.utn.frbb.tup.business.Implementation;
import ar.utn.frbb.tup.business.ProfesorService;
import ar.utn.frbb.tup.dto.ProfesorDTO;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.Implementation.ProfesorDaoImplementation;
import ar.utn.frbb.tup.persistence.ProfesorDao;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImplementation implements ProfesorService {

    @Autowired
    ProfesorDao profesorDao;

    @Override
    public Profesor crearProfesor(ProfesorDTO profesorDTO) throws ProfesorAlreadyExistsException {
        Profesor profesor = new Profesor();

        profesor.setId(profesorDTO.getId());
        profesor.setNombre(profesorDTO.getNombre());


        return this.profesorDao.crearProfesor(profesor);
    }
}
