package ar.utn.frbb.tup.business;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.MateriaDao;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.utn.frbb.tup.business.implementation.MateriaServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

public class MateriaServiceTest {

    @InjectMocks
    private MateriaServiceImplementation materiaService;

    @Mock
    private MateriaDao materiaDao;

    private MateriaDTO materiaDTO;
    private Materia materia;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        materiaDTO = new MateriaDTO();
        materiaDTO.setId(1);
        materiaDTO.setNombre("Matemáticas");
        materiaDTO.setAnio(2024);
        materiaDTO.setCuatrimestre(1);
        materiaDTO.setProfesorId(10);
        materiaDTO.setCorrelatividades(Arrays.asList(2, 3));

        materia = new Materia();
        materia.setId(1);
        materia.setNombre("Matemáticas");
        materia.setAnio(2024);
        materia.setCuatrimestre(1);
        materia.setProfesorId(10);
        materia.setCorrelatividades(Arrays.asList(2, 3));
    }

    @Test
    void testCrearMateria() throws MateriaAlreadyExistsException {
        when(materiaDao.crearMateria(any(Materia.class))).thenReturn(materia);

        Materia createdMateria = materiaService.crearMateria(materiaDTO);

        assertNotNull(createdMateria);
        assertEquals(materiaDTO.getId(), createdMateria.getId());
        verify(materiaDao, times(1)).crearMateria(any(Materia.class));
    }

    @Test
    void testEliminarMateria() throws MateriaNotFoundException {
        when(materiaDao.deleteMateria(anyInt())).thenReturn("Materia eliminada");

        String response = materiaService.eliminarMateria(1);

        assertEquals("Materia eliminada", response);
        verify(materiaDao, times(1)).deleteMateria(anyInt());
    }

    @Test
    void testGetMateriaById() {
        when(materiaDao.getMateriaById(anyInt())).thenReturn(materia);

        Materia foundMateria = materiaService.getMateriaById(1);

        assertNotNull(foundMateria);
        assertEquals(materia.getId(), foundMateria.getId());
        verify(materiaDao, times(1)).getMateriaById(anyInt());
    }
}
