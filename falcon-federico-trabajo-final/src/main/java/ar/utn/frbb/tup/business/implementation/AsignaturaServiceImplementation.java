package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.AsignaturaService;
import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.dto.AlumnoDTO;
import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.persistence.AsignaturaDao;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImplementation implements AsignaturaService {

    @Autowired
    AsignaturaDao asignaturaDao;

    @Autowired
    MateriaService materiaService;

    @Override
    public Asignatura crearAsignatura(AsignaturaDTO asignaturaDTO) {
        Asignatura a = new Asignatura();

        a.setId(asignaturaDTO.getId());

        a.setMateria(materiaService.getMateriaById(asignaturaDTO.getMateriaId()));
        a.setEstado(asignaturaDTO.getEstado());
        a.setNota(asignaturaDTO.getNota());

        return asignaturaDao.crearAsignatura(a);
    }
}
