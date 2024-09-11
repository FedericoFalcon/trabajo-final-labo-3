package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.ProfesorDao;
import ar.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;
import ar.utn.frbb.tup.persistence.implementation.ProfesorDaoImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfesorDaoTest {

    @InjectMocks
    private ProfesorDaoImplementation profesorDao;

    @Mock
    private MateriaService materiaService;

    private Map<Integer, Profesor> repoProfesores;
    private Profesor profesor;
    private Materia materia;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        repoProfesores = new HashMap<>();
        profesorDao = new ProfesorDaoImplementation();

        when(materiaService.getMateriaById(anyInt())).thenAnswer(invocation -> {
            Integer idMateria = invocation.getArgument(0);
            if (idMateria == 1) {
                return materia;
            } else {
                throw new MateriaNotFoundException("Error. La materia no existe.");
            }
        });

        profesor = new Profesor();
        profesor.setId(1);
        profesor.setMateriasDictadas(List.of(1));

        materia = new Materia();
        materia.setId(1);
        materia.setNombre("Matemáticas");
        materia.setAnio(2024);
        materia.setCuatrimestre(1);

        ProfesorDaoImplementation repoImpl = new ProfesorDaoImplementation() {
            @Override
            public Profesor crearProfesor(Profesor profesor) throws ProfesorAlreadyExistsException, MateriaNotFoundException {
                repoProfesores.put(profesor.getId(), profesor);
                return profesor;
            }

            @Override
            public Profesor getProfesorById(Integer idProfesor) throws ProfesorNotFoundException {
                return repoProfesores.get(idProfesor);
            }

            @Override
            public String deleteProfesor(Integer idProfesor) throws ProfesorNotFoundException {
                if (repoProfesores.containsKey(idProfesor)) {
                    repoProfesores.remove(idProfesor);
                    return "Profesor eliminado con exito";
                } else {
                    throw new ProfesorNotFoundException("Error. El profesor no existe.");
                }
            }

            @Override
            public List<Materia> getMateriasProfesor(Integer idProfesor) throws ProfesorNotFoundException, MateriaNotFoundException {
                Profesor p = repoProfesores.get(idProfesor);
                if (p == null) {
                    throw new ProfesorNotFoundException("Error. El profesor no existe.");
                }
                List<Materia> listaMaterias = new ArrayList<>();
                for (Integer idMateria : p.getMateriasDictadas()) {
                    listaMaterias.add(materiaService.getMateriaById(idMateria));
                }
                return listaMaterias;
            }
        };

        profesorDao = repoImpl;
    }

    @Test
    void testCrearProfesor() throws ProfesorAlreadyExistsException, MateriaNotFoundException {
        Profesor createdProfesor = profesorDao.crearProfesor(profesor);

        assertNotNull(createdProfesor);
        assertEquals(profesor.getId(), createdProfesor.getId());
        assertTrue(repoProfesores.containsKey(profesor.getId()));
    }

    @Test
    void testGetProfesorById() throws ProfesorNotFoundException {
        repoProfesores.put(profesor.getId(), profesor);

        Profesor foundProfesor = profesorDao.getProfesorById(profesor.getId());

        assertNotNull(foundProfesor);
        assertEquals(profesor.getId(), foundProfesor.getId());
    }

    @Test
    void testDeleteProfesor() throws ProfesorNotFoundException {
        repoProfesores.put(profesor.getId(), profesor);

        String result = profesorDao.deleteProfesor(profesor.getId());

        assertEquals("Profesor eliminado con exito", result);
        assertFalse(repoProfesores.containsKey(profesor.getId()));
    }

    @Test
    void testGetMateriasProfesor() throws ProfesorNotFoundException, MateriaNotFoundException {
        repoProfesores.put(profesor.getId(), profesor);

        List<Materia> materias = profesorDao.getMateriasProfesor(profesor.getId());

        assertNotNull(materias);
        assertEquals(1, materias.size());
    }
}
