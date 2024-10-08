package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.MateriaService;

import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.MateriaDao;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaServiceImplementation implements MateriaService {
    @Autowired
    MateriaDao materiaDao;

    @Override
    public Materia crearMateria(MateriaDTO materiaDTO) throws MateriaAlreadyExistsException {
        Materia materia = new Materia();

        materia.setId(materiaDTO.getId());
        materia.setNombre(materiaDTO.getNombre());
        materia.setAnio(materiaDTO.getAnio());
        materia.setCuatrimestre(materiaDTO.getCuatrimestre());
        materia.setProfesorId(materiaDTO.getProfesorId());
        materia.setCorrelatividades(materiaDTO.getCorrelatividades());

        return this.materiaDao.crearMateria(materia);
    }

    @Override
    public String eliminarMateria(Integer idMateria) throws MateriaNotFoundException {
        return materiaDao.deleteMateria(idMateria);
    }

    @Override
    public Materia getMateriaById(Integer idMateria) {
        return materiaDao.getMateriaById(idMateria);
    }

    @Override
    public Materia setProfesor(Integer idMateria, Integer idProfesor) {
        Materia m = materiaDao.getMateriaById(idMateria);
                m.setProfesorId(idProfesor);
                return  m;
    }
}
