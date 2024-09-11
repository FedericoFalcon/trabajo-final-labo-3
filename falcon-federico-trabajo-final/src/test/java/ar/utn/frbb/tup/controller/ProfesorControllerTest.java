package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.ProfesorService;
import ar.utn.frbb.tup.dto.ProfesorDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.model.Profesor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProfesorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfesorService profesorService;

    @Test
    void crearProfesor() throws Exception {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setId(1);
        profesorDTO.setNombre("Juan");
        profesorDTO.setApellido("Pérez");
        profesorDTO.setTitulo("Ingeniero");
        profesorDTO.setMateriasDictadas(List.of(101, 102, 103));

        Profesor profesor = new Profesor();
        profesor.setId(1);
        profesor.setNombre("Juan");
        profesor.setApellido("Pérez");
        profesor.setTitulo("Ingeniero");
        profesor.setMateriasDictadas(List.of(101, 102, 103));

        when(profesorService.crearProfesor(any(ProfesorDTO.class))).thenReturn(profesor);

        mockMvc.perform(post("/profesor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(profesorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Pérez"))
                .andExpect(jsonPath("$.titulo").value("Ingeniero"));
    }

    @Test
    void modificarProfesor() throws Exception {
        int idProfesor = 1;
        Map<String, Object> campos = Map.of("nombre", "Carlos", "apellido", "González");

        Profesor profesor = new Profesor();
        profesor.setId(idProfesor);
        profesor.setNombre("Carlos");
        profesor.setApellido("González");
        profesor.setTitulo("Doctor");
        profesor.setMateriasDictadas(List.of(101, 102, 103));

        when(profesorService.modificarProfesor(eq(idProfesor), anyMap())).thenReturn(profesor);

        mockMvc.perform(put("/profesor/{idProfesor}", idProfesor)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(campos)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Carlos"))
                .andExpect(jsonPath("$.apellido").value("González"));
    }

    @Test
    void eliminarProfesor() throws Exception {
        int idProfesor = 1;

        when(profesorService.eliminarProfesor(idProfesor)).thenReturn("Profesor eliminado correctamente");

        mockMvc.perform(delete("/profesor/{idProfesor}", idProfesor))
                .andExpect(status().isOk())
                .andExpect(content().string("Profesor eliminado correctamente"));
    }

    @Test
    void getMateriasProfesor() throws Exception {
        int idProfesor = 1;
        List<Materia> materias = List.of(
                new Materia("Matemáticas I", 2024, 1, idProfesor),
                new Materia("Física I", 2024, 1, idProfesor)
        );

        materias.get(0).setId(101);  // Asignando ID a la materia
        materias.get(1).setId(102);  // Asignando ID a la materia

        when(profesorService.getMateriasProfesor(idProfesor)).thenReturn(materias);

        mockMvc.perform(get("/profesor/materias")
                        .param("idProfesor", String.valueOf(idProfesor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre").value("Matemáticas I"))
                .andExpect(jsonPath("$[0].anio").value(2024))
                .andExpect(jsonPath("$[0].cuatrimestre").value(1))
                .andExpect(jsonPath("$[0].profesorId").value(idProfesor))
                .andExpect(jsonPath("$[1].nombre").value("Física I"))
                .andExpect(jsonPath("$[1].anio").value(2024))
                .andExpect(jsonPath("$[1].cuatrimestre").value(1))
                .andExpect(jsonPath("$[1].profesorId").value(idProfesor));
    }
}
