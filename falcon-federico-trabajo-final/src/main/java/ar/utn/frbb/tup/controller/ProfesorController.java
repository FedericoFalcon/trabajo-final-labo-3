package ar.utn.frbb.tup.controller;
import ar.utn.frbb.tup.business.ProfesorService;
import ar.utn.frbb.tup.dto.ProfesorDTO;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profesor")
public class ProfesorController {
    @Autowired // Inyeccion de dependencia (profesorService)
    ProfesorService profesorService;

    @PostMapping
    public Profesor crearProfesor(@RequestBody ProfesorDTO profesorDTO) throws ProfesorAlreadyExistsException {
        return profesorService.crearProfesor(profesorDTO);
    }

}
