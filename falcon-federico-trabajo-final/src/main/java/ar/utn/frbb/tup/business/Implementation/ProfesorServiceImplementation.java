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
import java.util.Map;

@Service
public class ProfesorServiceImplementation implements ProfesorService {

    @Autowired
    ProfesorDao profesorDao;

    @Override
    public Profesor crearProfesor(ProfesorDTO profesorDTO) throws ProfesorAlreadyExistsException {
        Profesor profesor = new Profesor();

        profesor.setId(profesorDTO.getId());
        profesor.setNombre(profesorDTO.getNombre());
        profesor.setApellido(profesorDTO.getApellido());
        profesor.setTitulo(profesorDTO.getTitulo());

        return this.profesorDao.crearProfesor(profesor);
    }

    @Override
    public Profesor modificarProfesor(Integer idProfesor, Map<String, Object> atributos) {
        Profesor profesor = profesorDao.getProfesorById(idProfesor);

        for (Map.Entry<String, Object> atributo : atributos.entrySet()) {
            String nombreAtributo = atributo.getKey();
            Object valor = atributo.getValue();
            modificarAtributos(nombreAtributo, valor, profesor);
        }

        return profesor;
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
}
