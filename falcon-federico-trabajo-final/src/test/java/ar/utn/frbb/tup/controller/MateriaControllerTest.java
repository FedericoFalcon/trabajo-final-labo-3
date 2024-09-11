package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.business.ProfesorService;
import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Materia;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MateriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MateriaService materiaService;

    @Test
    void crearMateria() throws Exception {
        MateriaDTO materiaDTO = new MateriaDTO();
        materiaDTO.setId(1);
        materiaDTO.setNombre("Matemáticas Avanzadas");
        materiaDTO.setAnio(2024);
        materiaDTO.setCuatrimestre(1);
        materiaDTO.setProfesorId(1001);
        materiaDTO.setCorrelatividades(List.of(101, 102));

        Materia materia = new Materia("Matemáticas Avanzadas", 2024, 1, 1001);
        materia.setId(1);
        materia.setCorrelatividades(List.of(101, 102));

        when(materiaService.crearMateria(any(MateriaDTO.class))).thenReturn(materia);

        mockMvc.perform(post("/materia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(materiaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Matemáticas Avanzadas"))
                .andExpect(jsonPath("$.anio").value(2024))
                .andExpect(jsonPath("$.cuatrimestre").value(1))
                .andExpect(jsonPath("$.profesorId").value(1001))
                .andExpect(jsonPath("$.correlatividades", hasSize(2)))
                .andExpect(jsonPath("$.correlatividades[0]").value(101))
                .andExpect(jsonPath("$.correlatividades[1]").value(102));
    }

    @Test
    void getMateria() throws Exception {
        int idMateria = 1;

        Materia materia = new Materia("Física II", 2024, 1, 1002);
        materia.setId(idMateria);
        materia.setCorrelatividades(List.of(201, 202));

        when(materiaService.getMateriaById(idMateria)).thenReturn(materia);

        mockMvc.perform(get("/materia/{idMateria}", idMateria))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Física II"))
                .andExpect(jsonPath("$.anio").value(2024))
                .andExpect(jsonPath("$.cuatrimestre").value(1))
                .andExpect(jsonPath("$.profesorId").value(1002))
                .andExpect(jsonPath("$.correlatividades", hasSize(2)))
                .andExpect(jsonPath("$.correlatividades[0]").value(201))
                .andExpect(jsonPath("$.correlatividades[1]").value(202));
    }

    @Test
    void eliminarMateria() throws Exception {
        int idMateria = 1;

        when(materiaService.eliminarMateria(idMateria)).thenReturn("Materia eliminada correctamente");

        mockMvc.perform(delete("/materia/{idMateria}", idMateria))
                .andExpect(status().isOk())
                .andExpect(content().string("Materia eliminada correctamente"));
    }
}