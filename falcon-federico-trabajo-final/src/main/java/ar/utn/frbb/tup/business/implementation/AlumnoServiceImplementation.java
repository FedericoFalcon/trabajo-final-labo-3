package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.*;
import ar.utn.frbb.tup.dto.AlumnoDTO;
import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.*;
import ar.utn.frbb.tup.persistence.AlumnoDao;
import ar.utn.frbb.tup.persistence.AsignaturaDao;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AlumnoServiceImplementation implements AlumnoService {

    @Autowired
    AlumnoDao alumnoDao;

    @Autowired
    AsignaturaDao asignaturaDao;

    @Autowired
    AsignaturaService asignaturaService;

    @Autowired
    CarreraService carreraService;

    @Autowired
    MateriaService materiaService;

    @Override
    public Alumno crearAlumno(AlumnoDTO alumnoDTO) throws AlumnoAlreadyExistsException {
        Alumno a = new Alumno();

        a.setId(alumnoDTO.getId());
        a.setNombre(alumnoDTO.getNombre());
        a.setApellido(alumnoDTO.getApellido());
        a.setDni(alumnoDTO.getDni());

        return alumnoDao.createAlumno(a);
    }

    @Override
    public Alumno modificarAlumno(Integer idAlumno, Map<String, Object> atributos) throws AlumnoNotFoundException {
        Alumno alumno = alumnoDao.getAlumnoById(idAlumno);

        for (Map.Entry<String, Object> atributo : atributos.entrySet()) {
            String nombreAtributo = atributo.getKey();
            Object valor = atributo.getValue();
            modificarAtributos(nombreAtributo, valor, alumno);
        }

        return alumno;
    }

    @Override
    public String eliminarAlumno(Integer idAlumno) throws AlumnoNotFoundException {
        return alumnoDao.deleteAlumno(idAlumno);
    }

    @Override
    public Alumno cambiarEstadoAsignatura(Integer idAlumno, Integer idAsignatura, AsignaturaDTO nuevoEstado) throws AlumnoNotFoundException {
        Alumno alumno = alumnoDao.getAlumnoById(idAlumno);
        Asignatura asignatura = asignaturaDao.getAsignaturaById(idAsignatura);

        if (alumno != null && asignatura != null) {
            asignatura.setEstado(nuevoEstado.getEstado());
            asignatura.setNota(nuevoEstado.getNota());
            return alumno;
        }
        return null;
    }


    private void modificarAtributos(String nombreAtributo, Object value, Alumno alumno) {
        switch (nombreAtributo) {
            case "nombre" -> {
                if (value instanceof String) {
                    alumno.setNombre((String) value);
                }
            }
            case "apellido" -> {
                if (value instanceof String) {
                    alumno.setApellido((String) value);
                }
            }
            case "dni" -> {
                if (value instanceof Integer) {
                    alumno.setDni((Integer) value);
                }
            }
            case "asignaturas" -> {
                if (value instanceof List) {
                    agregarAsignaturas(alumno, (List<Integer>) value);
                }
            }
        }
    }

    private void agregarAsignaturas(Alumno alumno, List<Integer> idMaterias) {
        for (Integer idMateria: idMaterias) {
            Asignatura asignatura = new Asignatura();
            asignatura.setMateria(materiaService.getMateriaById(idMateria));
            alumno.getAsignaturas().add(asignatura);
        }
    }
}
