package es.cic.grupo1.programacion_televisiva.controller;

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
import es.cic.grupo1.programacion_televisiva.model.TipoPrograma;
import es.cic.grupo1.programacion_televisiva.service.TipoProgramaService;

@RestController
@RequestMapping("/api/tipoprogramas")
public class TipoProgramaController {

    @Autowired
    private TipoProgramaService tipoProgramaService;

    @PostMapping
    public ResponseEntity<UUID> createTipoPrograma(@RequestBody TipoPrograma tipoPrograma) {
        TipoPrograma savedTipoPrograma = tipoProgramaService.saveTipoPrograma(tipoPrograma);
        return ResponseEntity.ok(savedTipoPrograma.getId());
    }

    @GetMapping
    public ResponseEntity<List<TipoPrograma>> getAllTipoProgramas() {
        List<TipoPrograma> tipoProgramas = tipoProgramaService.getAllTipoProgramas();
        return ResponseEntity.ok(tipoProgramas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPrograma> getProgramaById(@PathVariable UUID id) {
        Optional<TipoPrograma> programa = tipoProgramaService.getTipoProgramaById(id);
        return programa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // @GetMapping
    // public ResponseEntity<List<Programa>> getAllProgramasByTipo(TipoPrograma
    // tipoPrograma) {
    // List<Programa> programas =
    // tipoProgramaService.getAllProgramasByTipo(tipoPrograma);
    // return ResponseEntity.ok(programas);
    // }

    @GetMapping("/programas/{id}")
    public ResponseEntity<List<Programa>> getAllProgramasByTipo(@PathVariable UUID id) {
        Optional<TipoPrograma> optionalTipoPrograma = tipoProgramaService.getTipoProgramaById(id);
        if (optionalTipoPrograma.isPresent()) {
            TipoPrograma tipoPrograma = optionalTipoPrograma.get();
            List<Programa> programas = tipoProgramaService.getAllProgramasByTipo(tipoPrograma);
            return ResponseEntity.ok(programas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<TipoPrograma> updateTipoPrograma(@RequestBody TipoPrograma newTipoPrograma) throws Exception {

        Optional<TipoPrograma> existingTipoPrograma = tipoProgramaService.getTipoProgramaById(newTipoPrograma.getId());

        if (existingTipoPrograma.isPresent()) {
            // TipoPrograma programaToUpdate = existingTipoPrograma.get();
            // tipoPrograma.setFechaModificacion(LocalDateTime.now());
            // TODO: comprobar fechas de newTipoPrograma
            return ResponseEntity.ok(tipoProgramaService.updateTipoPrograma(newTipoPrograma));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoPrograma(@PathVariable UUID id) {

        Optional<TipoPrograma> existingTipoPrograma = tipoProgramaService.getTipoProgramaById(id);
        if (existingTipoPrograma.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        tipoProgramaService.deleteTipoPrograma(id);
        return ResponseEntity.noContent().build();
    }

}
