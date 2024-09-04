package es.cic.grupo1.programacion_televisiva;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.grupo1.programacion_televisiva.controller.TipoProgramaController;
import es.cic.grupo1.programacion_televisiva.model.TipoPrograma;
import es.cic.grupo1.programacion_televisiva.service.TipoProgramaService;

@WebMvcTest(TipoProgramaController.class)
public class TipoProgramaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoProgramaService tipoProgramaService;

    @Autowired
    private ObjectMapper objectMapper;

    private TipoPrograma tipoPrograma;

    @BeforeEach
    public void setUp() {
        tipoPrograma = new TipoPrograma();
        tipoPrograma.prePersist();
        tipoPrograma.setNombre("Documental");
        tipoPrograma.setDescripcion("Programas educativos y de información factual");
    }

    @Test
    public void testCreateTipoPrograma() throws Exception {
        // Configura el comportamiento del servicio mock
        when(tipoProgramaService.saveTipoPrograma(any(TipoPrograma.class))).thenReturn(tipoPrograma);

        // Realiza la solicitud POST y captura el resultado
        MvcResult result = mockMvc.perform(post("/api/tipoprogramas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoPrograma)))
                .andExpect(status().isOk())
                .andReturn();

        // Obtén la respuesta JSON (el UUID en este caso)
        String jsonResponse = result.getResponse().getContentAsString();
        System.out.println("UUID Response: " + jsonResponse);

        // Verifica que el UUID devuelto no esté vacío
        assertNotNull(jsonResponse);
        assertFalse(jsonResponse.isEmpty());
    }

    @Test
    public void testGetAllTipoProgramas() throws Exception {
        when(tipoProgramaService.getAllTipoProgramas()).thenReturn(Arrays.asList(tipoPrograma));

        mockMvc.perform(get("/api/tipoprogramas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value(tipoPrograma.getNombre()));
    }

    @Test
    public void testGetProgramaById() throws Exception {
        when(tipoProgramaService.getTipoProgramaById(tipoPrograma.getId())).thenReturn(Optional.of(tipoPrograma));

        mockMvc.perform(get("/api/tipoprogramas/" + tipoPrograma.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(tipoPrograma.getNombre()));
    }

    @Test
    public void testUpdateTipoPrograma() throws Exception {
        when(tipoProgramaService.getTipoProgramaById(tipoPrograma.getId())).thenReturn(Optional.of(tipoPrograma));
        when(tipoProgramaService.updateTipoPrograma(any(TipoPrograma.class))).thenReturn(tipoPrograma);

        mockMvc.perform(put("/api/tipoprogramas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoPrograma)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(tipoPrograma.getNombre()));
    }

    @Test
    public void testDeleteTipoPrograma() throws Exception {
        when(tipoProgramaService.getTipoProgramaById(tipoPrograma.getId())).thenReturn(Optional.of(tipoPrograma));

        mockMvc.perform(delete("/api/tipoprogramas/" + tipoPrograma.getId()))
                .andExpect(status().isNoContent());
    }
}