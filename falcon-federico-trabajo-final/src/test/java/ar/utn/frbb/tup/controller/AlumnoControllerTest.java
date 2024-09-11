package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.AlumnoService;
import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.dto.AlumnoDTO;
import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Asignatura;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AlumnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoService alumnoService;

    @Test
    void crearAlumno() throws Exception {
        AlumnoDTO alumnoDTO = new AlumnoDTO();
        alumnoDTO.setId(1);
        alumnoDTO.setNombre("Juan");
        alumnoDTO.setApellido("Pérez");
        alumnoDTO.setDni(12345678);
        alumnoDTO.setMaterias(List.of(101, 102));

        Alumno alumno = new Alumno(1, "Juan", "Pérez", 12345678, List.of());

        when(alumnoService.crearAlumno(any(AlumnoDTO.class))).thenReturn(alumno);

        mockMvc.perform(post("/alumno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(alumnoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Pérez"))
                .andExpect(jsonPath("$.dni").value(12345678));
    }


    @Test
    void modificarAlumno() throws Exception {
        int idAlumno = 1;
        Map<String, Object> campos = Map.of("nombre", "Carlos");

        Alumno alumnoModificado = new Alumno(1, "Carlos", "Pérez", 12345678, List.of());

        when(alumnoService.modificarAlumno(eq(idAlumno), any(Map.class))).thenReturn(alumnoModificado);

        mockMvc.perform(put("/alumno/{idAlumno}", idAlumno)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(campos)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Carlos"))
                .andExpect(jsonPath("$.apellido").value("Pérez"))
                .andExpect(jsonPath("$.dni").value(12345678));
    }

    @Test
    void eliminarAlumno() throws Exception {
        int idAlumno = 1;

        when(alumnoService.eliminarAlumno(idAlumno)).thenReturn("Alumno eliminado correctamente");

        mockMvc.perform(delete("/alumno/{idAlumno}", idAlumno))
                .andExpect(status().isOk())
                .andExpect(content().string("Alumno eliminado correctamente"));
    }
}