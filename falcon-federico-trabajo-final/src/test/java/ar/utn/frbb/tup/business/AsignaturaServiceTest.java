package ar.utn.frbb.tup.business;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.model.EstadoAsignatura;
import ar.utn.frbb.tup.persistence.AsignaturaDao;
import ar.utn.frbb.tup.business.implementation.AsignaturaServiceImplementation;
import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.model.Materia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

public class AsignaturaServiceTest {

    @InjectMocks
    private AsignaturaServiceImplementation asignaturaService;

    @Mock
    private AsignaturaDao asignaturaDao;

    @Mock
    private MateriaService materiaService;

    private AsignaturaDTO asignaturaDTO;
    private Asignatura asignatura;
    private Materia materia;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        materia = new Materia(); // Usa el constructor por defecto
        materia.setId(1);
        materia.setNombre("Matemáticas");
        materia.setAnio(2024);
        materia.setCuatrimestre(1);
        materia.setProfesorId(10);
        materia.setCorrelatividades(Arrays.asList(2, 3));

        asignaturaDTO = new AsignaturaDTO(); // Usa el constructor por defecto
        asignaturaDTO.setId(1);
        asignaturaDTO.setMateriaId(1);
        asignaturaDTO.setEstado(EstadoAsignatura.NO_CURSADA);
        asignaturaDTO.setNota(null);

        asignatura = new Asignatura(); // Usa el constructor por defecto
        asignatura.setId(1);
        asignatura.setMateria(materia);
        asignatura.setEstado(EstadoAsignatura.NO_CURSADA);
        asignatura.setNota(null);
    }

    @Test
    void testCrearAsignatura() {
        when(materiaService.getMateriaById(anyInt())).thenReturn(materia);
        when(asignaturaDao.crearAsignatura(any(Asignatura.class))).thenReturn(asignatura);

        Asignatura createdAsignatura = asignaturaService.crearAsignatura(asignaturaDTO);

        assertNotNull(createdAsignatura);
        assertEquals(asignaturaDTO.getId(), createdAsignatura.getId());
        assertEquals(materia, createdAsignatura.getMateria());
        verify(materiaService, times(1)).getMateriaById(anyInt());
        verify(asignaturaDao, times(1)).crearAsignatura(any(Asignatura.class));
    }
}
