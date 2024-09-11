package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.dto.AlumnoDTO;
import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.model.EstadoAsignatura;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.AlumnoDao;
import ar.utn.frbb.tup.persistence.AsignaturaDao;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import ar.utn.frbb.tup.business.implementation.AlumnoServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class AlumnoServiceTest {

    @InjectMocks
    private AlumnoServiceImplementation alumnoService;

    @Mock
    private AlumnoDao alumnoDao;

    @Mock
    private AsignaturaDao asignaturaDao;

    @Mock
    private AsignaturaService asignaturaService;

    private AlumnoDTO alumnoDTO;
    private Alumno alumno;
    private Asignatura asignatura;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear instancias con constructores por defecto
        alumnoDTO = new AlumnoDTO();
        alumnoDTO.setId(1);
        alumnoDTO.setNombre("Jose");
        alumnoDTO.setApellido("Lopez");
        alumnoDTO.setDni(12345678);
        alumnoDTO.setMaterias(Arrays.asList(1, 2, 3));

        alumno = new Alumno();
        alumno.setId(1);
        alumno.setNombre("Jose");
        alumno.setApellido("Lopez");
        alumno.setDni(12345678);
        alumno.setAsignaturas(Arrays.asList(
                new Asignatura(1, new Materia(), EstadoAsignatura.NO_CURSADA, null),
                new Asignatura(2, new Materia(), EstadoAsignatura.NO_CURSADA, null),
                new Asignatura(3, new Materia(), EstadoAsignatura.NO_CURSADA, null)
        ));

        asignatura = new Asignatura();
        asignatura.setId(1);
        asignatura.setMateria(new Materia());
        asignatura.setEstado(EstadoAsignatura.NO_CURSADA);
        asignatura.setNota(null);
    }

    @Test
    void testCrearAlumno() throws AlumnoAlreadyExistsException {
        when(alumnoDao.createAlumno(any(Alumno.class))).thenReturn(alumno);
        when(asignaturaService.crearAsignatura(any(AsignaturaDTO.class))).thenReturn(asignatura);

        Alumno result = alumnoService.crearAlumno(alumnoDTO);

        assertNotNull(result);
        assertEquals(alumnoDTO.getId(), result.getId());
        assertEquals(alumnoDTO.getNombre(), result.getNombre());
        assertEquals(alumnoDTO.getApellido(), result.getApellido());
        verify(alumnoDao).createAlumno(any(Alumno.class));
        verify(asignaturaService, times(alumnoDTO.getMaterias().size())).crearAsignatura(any(AsignaturaDTO.class));
    }

    @Test
    void testModificarAlumno() throws AlumnoNotFoundException {
        Map<String, Object> campos = new HashMap<>();
        campos.put("nombre", "Pepe");
        when(alumnoDao.getAlumnoById(anyInt())).thenReturn(alumno);

        Alumno result = alumnoService.modificarAlumno(1, campos);

        assertNotNull(result);
        assertEquals("Pepe", result.getNombre());
        verify(alumnoDao).getAlumnoById(anyInt());
    }

    @Test
    void testEliminarAlumno() throws AlumnoNotFoundException {
        when(alumnoDao.deleteAlumno(anyInt())).thenReturn("Alumno eliminado con exito");

        String result = alumnoService.eliminarAlumno(1);

        assertEquals("Alumno eliminado con exito", result);
        verify(alumnoDao).deleteAlumno(anyInt());
    }

}
