package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.AsignaturaService;
import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.model.EstadoAsignatura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class AsignaturaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AsignaturaService asignaturaService;

    @InjectMocks
    private AsignaturaController asignaturaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(asignaturaController).build();
    }

    @Test
    void crearAsignatura() throws Exception {
        // Datos de entrada
        AsignaturaDTO asignaturaDTO = new AsignaturaDTO(1, 101, EstadoAsignatura.CURSADA, 8);

        // Objeto esperado
        Asignatura asignatura = new Asignatura(1, null, EstadoAsignatura.CURSADA, 8);

        // Mockear la respuesta del servicio
        when(asignaturaService.crearAsignatura(any(AsignaturaDTO.class))).thenReturn(asignatura);

        // Realizar la solicitud POST
        mockMvc.perform(post("/asignatura")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(asignaturaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(asignatura.getId()))
                .andExpect(jsonPath("$.estado").value(asignatura.getEstado().toString()))
                .andExpect(jsonPath("$.nota").value(asignatura.getNota()));

        // Verificar que el servicio fue llamado correctamente
        verify(asignaturaService, times(1)).crearAsignatura(any(AsignaturaDTO.class));
    }
}
