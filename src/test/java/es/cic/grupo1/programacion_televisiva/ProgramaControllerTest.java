package es.cic.grupo1.programacion_televisiva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.grupo1.programacion_televisiva.controller.ProgramaController;
import es.cic.grupo1.programacion_televisiva.model.Programa;
import es.cic.grupo1.programacion_televisiva.service.ProgramaService;

@WebMvcTest(ProgramaController.class)
public class ProgramaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProgramaService programaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Programa programa;

    @BeforeEach
    public void setUp() {
        programa = new Programa();
        programa.prePersist();
        programa.setNombre("Programa de Prueba");
        programa.setDescripcion("Descripción del programa de prueba");
        programa.setFechaIni(new Timestamp(System.currentTimeMillis()));
        programa.setFechaFin(new Timestamp(System.currentTimeMillis() + 3600000)); // 1 hora después
    }

    @Test
    public void testCreatePrograma() throws Exception {
        when(programaService.savePrograma(any(Programa.class))).thenReturn(programa);

        mockMvc.perform(post("/api/programas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(programa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(programa.getId().toString()));
    }

    @Test
    public void testGetAllProgramas() throws Exception {
        List<Programa> programas = Arrays.asList(programa);
        when(programaService.getAllProgramas()).thenReturn(programas);

        mockMvc.perform(get("/api/programas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(programa.getId().toString()))
                .andExpect(jsonPath("$[0].nombre").value("Programa de Prueba"))
                .andExpect(jsonPath("$[0].descripcion").value("Descripción del programa de prueba"));
    }

    @Test
    public void testGetProgramaById() throws Exception {
        when(programaService.getProgramaById(any(UUID.class))).thenReturn(Optional.of(programa));

        mockMvc.perform(get("/api/programas/{id}", programa.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(programa.getId().toString()))
                .andExpect(jsonPath("$.nombre").value("Programa de Prueba"))
                .andExpect(jsonPath("$.descripcion").value("Descripción del programa de prueba"));
    }

    @Test
    public void testUpdatePrograma() throws Exception {
        when(programaService.getProgramaById(any(UUID.class))).thenReturn(Optional.of(programa));
        when(programaService.savePrograma(any(Programa.class))).thenReturn(programa);

        programa.setNombre("Programa Actualizado");
        programa.setDescripcion("Descripción actualizada");

        mockMvc.perform(put("/api/programas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(programa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(programa.getId().toString()))
                .andExpect(jsonPath("$.nombre").value("Programa Actualizado"))
                .andExpect(jsonPath("$.descripcion").value("Descripción actualizada"));
    }

    @Test
    public void testDeletePrograma() throws Exception {
        when(programaService.getProgramaById(any(UUID.class))).thenReturn(Optional.of(programa));

        mockMvc.perform(delete("/api/programas/{id}", programa.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}