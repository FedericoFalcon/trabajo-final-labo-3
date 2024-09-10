package ar.utn.frbb.tup.controller;
import ar.utn.frbb.tup.business.ProfesorService;
import ar.utn.frbb.tup.dto.ProfesorDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("profesor")
public class ProfesorController {

    @Autowired // Inyeccion de dependencia (profesorService)
    ProfesorService profesorService;

    @PostMapping
    public Profesor crearProfesor(@RequestBody ProfesorDTO profesorDTO) throws ProfesorAlreadyExistsException, MateriaNotFoundException {
        return profesorService.crearProfesor(profesorDTO);
    }

    @PutMapping("/{idProfesor}")
    public Profesor modificarProfesor(@PathVariable Integer idProfesor, @RequestBody Map<String,Object> campos) throws ProfesorNotFoundException {
        return profesorService.modificarProfesor(idProfesor, campos);
    }

    @DeleteMapping("/{idProfesor}")
    public String eliminarProfesor(@PathVariable Integer idProfesor) throws ProfesorNotFoundException {
        return profesorService.eliminarProfesor(idProfesor);
    }

    @GetMapping("/{idProfesor}/materias")
    public List<Materia> getMateriasProfesor(@PathVariable Integer idProfesor) throws ProfesorNotFoundException, MateriaNotFoundException {
        return profesorService.getMateriasProfesor(idProfesor);
    }

}
