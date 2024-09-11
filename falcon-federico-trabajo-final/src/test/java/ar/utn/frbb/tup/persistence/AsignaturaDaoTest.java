package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.model.EstadoAsignatura;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.AsignaturaDao;
import ar.utn.frbb.tup.persistence.implementation.AsignaturaDaoImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AsignaturaDaoTest {

    private AsignaturaDao asignaturaDao;
    private Map<Integer, Asignatura> repoAsignaturas;
    private Asignatura asignatura;

    @BeforeEach
    void setUp() {

        repoAsignaturas = new HashMap<>();


        AsignaturaDaoImplementation repoImpl = new AsignaturaDaoImplementation() {
            @Override
            public Asignatura getAsignaturaById(Integer idAsignatura) {
                return repoAsignaturas.get(idAsignatura);
            }

            @Override
            public Asignatura crearAsignatura(Asignatura asignatura) {
                repoAsignaturas.put(asignatura.getId(), asignatura);
                return asignatura;
            }
        };

        asignaturaDao = repoImpl;


        asignatura = new Asignatura();
        asignatura.setId(1);
        asignatura.setMateria(new Materia());
        asignatura.setEstado(EstadoAsignatura.NO_CURSADA);
        asignatura.setNota(null);
    }

    @Test
    void testCrearAsignatura() {
        Asignatura createdAsignatura = asignaturaDao.crearAsignatura(asignatura);

        assertNotNull(createdAsignatura);
        assertEquals(asignatura.getId(), createdAsignatura.getId());
        assertTrue(repoAsignaturas.containsKey(asignatura.getId()));
    }

    @Test
    void testGetAsignaturaById() {
        repoAsignaturas.put(asignatura.getId(), asignatura);

        Asignatura foundAsignatura = asignaturaDao.getAsignaturaById(asignatura.getId());

        assertNotNull(foundAsignatura);
        assertEquals(asignatura.getId(), foundAsignatura.getId());
    }

    @Test
    void testGetAsignaturaByIdNotFound() {
        assertNull(asignaturaDao.getAsignaturaById(999));
    }
}
