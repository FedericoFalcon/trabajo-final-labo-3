package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.business.implementation.ProfesorServiceImplementation;
import ar.utn.frbb.tup.dto.ProfesorDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.ProfesorDao;
import ar.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfesorServiceTest {

    @Mock
    private ProfesorDao profesorDao;

    @InjectMocks
    private ProfesorServiceImplementation profesorService;

    private ProfesorDTO profesorDTO;
    private Profesor profesor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Usar constructor por defecto
        profesorDTO = new ProfesorDTO();
        profesorDTO.setId(1);
        profesorDTO.setNombre("Marcelo");
        profesorDTO.setApellido("Perez");
        profesorDTO.setTitulo("Ing");
        profesorDTO.setMateriasDictadas(Arrays.asList(1, 2, 3));

        profesor = new Profesor();
        profesor.setId(1);
        profesor.setNombre("Marcelo");
        profesor.setApellido("Perez");
        profesor.setTitulo("Ing");
        profesor.setMateriasDictadas(Arrays.asList(1, 2, 3));
    }

    @Test
    void testCrearProfesorExitoso() throws ProfesorAlreadyExistsException, MateriaNotFoundException {
        when(profesorDao.crearProfesor(any(Profesor.class))).thenReturn(profesor);

        Profesor result = profesorService.crearProfesor(profesorDTO);

        assertNotNull(result);
        assertEquals(profesorDTO.getId(), result.getId());
        verify(profesorDao, times(1)).crearProfesor(any(Profesor.class));
    }

    @Test
    void testCrearProfesorYaExiste() throws ProfesorAlreadyExistsException, MateriaNotFoundException {
        when(profesorDao.crearProfesor(any(Profesor.class))).thenThrow(new ProfesorAlreadyExistsException("No se puede crear. El profesor ya existe."));

        assertThrows(ProfesorAlreadyExistsException.class, () -> profesorService.crearProfesor(profesorDTO));

        verify(profesorDao, times(1)).crearProfesor(any(Profesor.class));
    }

    @Test
    void testModificarProfesorExitoso() throws ProfesorNotFoundException {
        Map<String, Object> cambios = new HashMap<>();
        cambios.put("nombre", "Juan");

        when(profesorDao.getProfesorById(anyInt())).thenReturn(profesor);

        Profesor result = profesorService.modificarProfesor(1, cambios);

        assertEquals("Juan", result.getNombre());
        verify(profesorDao, times(1)).getProfesorById(anyInt());
    }

    @Test
    void testModificarProfesorNoEncontrado() throws ProfesorNotFoundException {
        when(profesorDao.getProfesorById(anyInt())).thenThrow(new ProfesorNotFoundException("El id ingresado no corresponde a ningun profesor"));

        assertThrows(ProfesorNotFoundException.class, () -> profesorService.modificarProfesor(1, new HashMap<>()));

        verify(profesorDao, times(1)).getProfesorById(anyInt());
    }

    @Test
    void testEliminarProfesorExitoso() throws ProfesorNotFoundException {
        when(profesorDao.deleteProfesor(anyInt())).thenReturn("Profesor eliminado con exito");

        String result = profesorService.eliminarProfesor(1);

        assertEquals("Profesor eliminado con exito", result);
        verify(profesorDao, times(1)).deleteProfesor(anyInt());
    }

    @Test
    void testEliminarProfesorNoEncontrado() throws ProfesorNotFoundException {
        when(profesorDao.deleteProfesor(anyInt())).thenThrow(new ProfesorNotFoundException("Error. El profesor no existe."));

        assertThrows(ProfesorNotFoundException.class, () -> profesorService.eliminarProfesor(1));

        verify(profesorDao, times(1)).deleteProfesor(anyInt());
    }

    @Test
    void testGetMateriasProfesorExitoso() throws ProfesorNotFoundException, MateriaNotFoundException {
        List<Materia> materias = Arrays.asList(new Materia("Antonio", 1, 1, 1));
        when(profesorDao.getMateriasProfesor(anyInt())).thenReturn(materias);

        List<Materia> result = profesorService.getMateriasProfesor(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(profesorDao, times(1)).getMateriasProfesor(anyInt());
    }

    @Test
    void testGetMateriasProfesorNoEncontrado() throws ProfesorNotFoundException, MateriaNotFoundException {
        when(profesorDao.getMateriasProfesor(anyInt())).thenThrow(new ProfesorNotFoundException("Error. El profesor no existe."));

        assertThrows(ProfesorNotFoundException.class, () -> profesorService.getMateriasProfesor(1));

        verify(profesorDao, times(1)).getMateriasProfesor(anyInt());
    }
}
