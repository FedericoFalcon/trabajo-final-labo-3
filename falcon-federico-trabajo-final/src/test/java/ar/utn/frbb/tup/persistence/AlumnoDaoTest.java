package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.persistence.AlumnoDao;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import ar.utn.frbb.tup.persistence.implementation.AlumnoDaoImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlumnoDaoTest {

    @InjectMocks
    private AlumnoDaoImplementation alumnoDao;

    private Map<Integer, Alumno> repositorioAlumnos;
    private Alumno alumno;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        repositorioAlumnos = new HashMap<>();

        AlumnoDaoImplementation repoImpl = new AlumnoDaoImplementation() {
            @Override
            public Alumno createAlumno(Alumno a) throws AlumnoAlreadyExistsException {
                if (repositorioAlumnos.containsKey(a.getId())) {
                    throw new AlumnoAlreadyExistsException("El alumno ya existe en la base de datos");
                }
                repositorioAlumnos.put(a.getId(), a);
                return a;
            }

            @Override
            public Alumno getAlumnoById(Integer idAlumno) throws AlumnoNotFoundException {
                if (!repositorioAlumnos.containsKey(idAlumno)) {
                    throw new AlumnoNotFoundException("El id del alumno no existe");
                }
                return repositorioAlumnos.get(idAlumno);
            }

            @Override
            public String deleteAlumno(Integer idAlumno) throws AlumnoNotFoundException {
                if (!repositorioAlumnos.containsKey(idAlumno)) {
                    throw new AlumnoNotFoundException("El id ingresado no pertenece a ningun alumno");
                }
                repositorioAlumnos.remove(idAlumno);
                return "Alumno eliminado con exito";
            }
        };

        alumnoDao = repoImpl;

        alumno = new Alumno();
        alumno.setId(1);
        alumno.setNombre("Juan");
        alumno.setApellido("Garcia");
        alumno.setDni(12345678);
    }

    @Test
    void testCreateAlumno() throws AlumnoAlreadyExistsException {
        Alumno createdAlumno = alumnoDao.createAlumno(alumno);

        assertNotNull(createdAlumno);
        assertEquals(alumno.getId(), createdAlumno.getId());
        assertTrue(repositorioAlumnos.containsKey(alumno.getId()));
    }

    @Test
    void testGetAlumnoById() throws AlumnoNotFoundException {
        repositorioAlumnos.put(alumno.getId(), alumno);

        Alumno foundAlumno = alumnoDao.getAlumnoById(alumno.getId());

        assertNotNull(foundAlumno);
        assertEquals(alumno.getId(), foundAlumno.getId());
    }

    @Test
    void testDeleteAlumno() throws AlumnoNotFoundException {
        repositorioAlumnos.put(alumno.getId(), alumno);

        String result = alumnoDao.deleteAlumno(alumno.getId());

        assertEquals("Alumno eliminado con exito", result);
        assertFalse(repositorioAlumnos.containsKey(alumno.getId()));
    }

    @Test
    void testCreateAlumnoAlreadyExists() throws AlumnoAlreadyExistsException {
        repositorioAlumnos.put(alumno.getId(), alumno);

        Alumno newAlumno = new Alumno();
        newAlumno.setId(alumno.getId());

        assertThrows(AlumnoAlreadyExistsException.class, () -> {
            alumnoDao.createAlumno(newAlumno);
        });
    }

    @Test
    void testGetAlumnoByIdNotFound() {
        assertThrows(AlumnoNotFoundException.class, () -> {
            alumnoDao.getAlumnoById(999);
        });
    }

    @Test
    void testDeleteAlumnoNotFound() {
        assertThrows(AlumnoNotFoundException.class, () -> {
            alumnoDao.deleteAlumno(999);
        });
    }
}
