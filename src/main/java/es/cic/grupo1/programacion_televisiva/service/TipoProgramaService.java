package es.cic.grupo1.programacion_televisiva.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo1.programacion_televisiva.model.TipoPrograma;
import es.cic.grupo1.programacion_televisiva.repository.TipoProgramaRepository;

@Service
public class TipoProgramaService {

    @Autowired
    TipoProgramaRepository tipoProgramaRepository;

    public TipoPrograma saveTipoPrograma(TipoPrograma tipoPrograma) {
        tipoPrograma.setFechaCreacion(LocalDateTime.now());
        tipoPrograma.setFechaModificacion(null);
        return tipoProgramaRepository.save(tipoPrograma);
    }

    public List<TipoPrograma> getAllTipoProgramas() {
        return tipoProgramaRepository.findAll();
    }

    public Optional<TipoPrograma> getTipoProgramaById(UUID id) {
        return tipoProgramaRepository.findById(id);
    }

    public void deleteTipoPrograma(UUID id) {
        tipoProgramaRepository.deleteById(id);
    }

    public TipoPrograma updateTipoPrograma(TipoPrograma tipoPrograma) {
        tipoPrograma.setFechaModificacion(LocalDateTime.now());
        return tipoProgramaRepository.save(tipoPrograma);
    }

}
