package es.cic.grupo1.programacion_televisiva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.cic.grupo1.programacion_televisiva.model.TipoPrograma;

public class TipoProgramaTest {

    private TipoPrograma tipoPrograma;

    @BeforeEach
    public void setUp() {
        tipoPrograma = new TipoPrograma();
    }

    @Test
    public void testGettersAndSetters() {
        String nombre = "Documental";
        String descripcion = "Programas educativos y de información factual";
        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDateTime fechaModificacion = LocalDateTime.now();

        tipoPrograma.setNombre(nombre);
        tipoPrograma.setDescripcion(descripcion);
        tipoPrograma.setFechaCreacion(fechaCreacion);
        tipoPrograma.setFechaModificacion(fechaModificacion);

        assertEquals(nombre, tipoPrograma.getNombre());
        assertEquals(descripcion, tipoPrograma.getDescripcion());
        assertEquals(fechaCreacion, tipoPrograma.getFechaCreacion());
        assertEquals(fechaModificacion, tipoPrograma.getFechaModificacion());
    }

    @Test
    public void testPrePersist() {
        tipoPrograma.prePersist();
        assertNotNull(tipoPrograma.getId());
    }

    @Test
    public void testConstructor() {
        String nombre = "Serie";
        String descripcion = "Series de ficción y entretenimiento";
        LocalDateTime fechaCreacion = LocalDateTime.now();

        TipoPrograma tipoPrograma = new TipoPrograma(nombre, descripcion, fechaCreacion);

        assertEquals(nombre, tipoPrograma.getNombre());
        assertEquals(descripcion, tipoPrograma.getDescripcion());
        assertEquals(fechaCreacion, tipoPrograma.getFechaCreacion());
    }
}