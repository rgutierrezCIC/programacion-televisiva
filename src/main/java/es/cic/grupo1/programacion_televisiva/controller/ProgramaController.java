package es.cic.grupo1.programacion_televisiva.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.grupo1.programacion_televisiva.model.Programa;
import es.cic.grupo1.programacion_televisiva.service.ProgramaService;

@RestController
@RequestMapping("/api/programas")
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    @PostMapping
    public ResponseEntity<UUID> createPrograma(@RequestBody Programa programa) {
        programa.setFechaIni(new Timestamp(System.currentTimeMillis()));
        Programa savedPrograma = programaService.savePrograma(programa);
        return ResponseEntity.ok(savedPrograma.getId());
    }

    @GetMapping
    public ResponseEntity<List<Programa>> getAllProgramas() {
        List<Programa> programas = programaService.getAllProgramas();
        return ResponseEntity.ok(programas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Programa> getProgramaById(@PathVariable java.util.UUID id) {
        Optional<Programa> programa = programaService.getProgramaById(id);
        return programa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Programa> updatePrograma(@RequestBody Programa programa) throws Exception {

        Optional<Programa> existingPrograma = programaService.getProgramaById(programa.getId());
        Programa modifiedProgram;
        if (programa.getId() == null) {
            throw new Exception("Me la has intentado colar");
        }
        if (existingPrograma.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            modifiedProgram = existingPrograma.get();
            modifiedProgram.setFavorito(programa.isFavorito());
            modifiedProgram.setCanal(programa.getCanal());
            modifiedProgram.setDescripcion(programa.getDescripcion());
            modifiedProgram.setNombre(programa.getNombre());
            modifiedProgram.setFechaFin(programa.getFechaFin());
            modifiedProgram.setFechaIni(programa.getFechaIni());
            modifiedProgram.setClasificacion(programa.getClasificacion());
            modifiedProgram.setSemanal(programa.isSemanal());
            modifiedProgram.setDias(programa.getDias());
            if (programa.getTipoPrograma() != null) {
                modifiedProgram.setTipoPrograma(programa.getTipoPrograma());
            }
        }

        Programa updatedPrograma = programaService.savePrograma(modifiedProgram);
        return ResponseEntity.ok(updatedPrograma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrograma(@PathVariable UUID id) {

        Optional<Programa> existingPrograma = programaService.getProgramaById(id);
        if (existingPrograma.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
   
        programaService.deletePrograma(id);
        return ResponseEntity.noContent().build();
    }
}