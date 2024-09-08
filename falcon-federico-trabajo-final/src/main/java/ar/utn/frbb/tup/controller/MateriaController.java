package ar.utn.frbb.tup.controller;


import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("materia")
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @PostMapping
    public Materia crearMateria(@RequestBody MateriaDTO materiaDTO) throws MateriaAlreadyExistsException {
        return materiaService.crearMateria(materiaDTO);
    }
}
