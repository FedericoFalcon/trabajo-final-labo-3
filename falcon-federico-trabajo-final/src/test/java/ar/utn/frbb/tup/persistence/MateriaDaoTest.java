package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.MateriaDao;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.utn.frbb.tup.persistence.implementation.MateriaDaoImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MateriaDaoTest {

    private MateriaDao materiaDao;
    private Map<Integer, Materia> repoMaterias;
    private Materia materia;

    @BeforeEach
    void setUp() {

        repoMaterias = new HashMap<>();


        MateriaDaoImplementation repoImpl = new MateriaDaoImplementation() {
            @Override
            public Materia crearMateria(Materia materia) throws MateriaAlreadyExistsException {
                if (repoMaterias.containsKey(materia.getId())) {
                    throw new MateriaAlreadyExistsException("No se puede crear. La materia ya existe.");
                }
                repoMaterias.put(materia.getId(), materia);
                return materia;
            }

            @Override
            public String deleteMateria(Integer idMateria) throws MateriaNotFoundException {
                if (idMateria == null || !repoMaterias.containsKey(idMateria)) {
                    throw new MateriaNotFoundException("Error. La materia no existe.");
                }
                repoMaterias.remove(idMateria);
                return "Materia eliminada con exito";
            }

            @Override
            public Materia getMateriaById(Integer idMateria) {
                return repoMaterias.get(idMateria);
            }
        };

        materiaDao = repoImpl;

        materia = new Materia();
        materia.setId(1);
        materia.setNombre("Matemáticas");
        materia.setAnio(2024);
        materia.setCuatrimestre(1);
        materia.setProfesorId(101);
    }

    @Test
    void testCrearMateria() throws MateriaAlreadyExistsException {
        Materia createdMateria = materiaDao.crearMateria(materia);

        assertNotNull(createdMateria);
        assertEquals(materia.getId(), createdMateria.getId());
        assertTrue(repoMaterias.containsKey(materia.getId()));
    }

    @Test
    void testGetMateriaById() {
        repoMaterias.put(materia.getId(), materia);

        Materia foundMateria = materiaDao.getMateriaById(materia.getId());

        assertNotNull(foundMateria);
        assertEquals(materia.getId(), foundMateria.getId());
    }

    @Test
    void testDeleteMateria() throws MateriaNotFoundException {
        repoMaterias.put(materia.getId(), materia);

        String result = materiaDao.deleteMateria(materia.getId());

        assertEquals("Materia eliminada con exito", result);
        assertFalse(repoMaterias.containsKey(materia.getId()));
    }

    @Test
    void testCrearMateriaAlreadyExists() throws MateriaAlreadyExistsException {
        repoMaterias.put(materia.getId(), materia);

        Materia newMateria = new Materia();
        newMateria.setId(materia.getId());

        assertThrows(MateriaAlreadyExistsException.class, () -> {
            materiaDao.crearMateria(newMateria);
        });
    }

    @Test
    void testGetMateriaByIdNotFound() {
        assertNull(materiaDao.getMateriaById(999));
    }

    @Test
    void testDeleteMateriaNotFound() {
        assertThrows(MateriaNotFoundException.class, () -> {
            materiaDao.deleteMateria(999);
        });
    }
}
