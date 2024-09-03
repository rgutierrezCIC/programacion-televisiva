package es.cic.grupo1.programacion_televisiva.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.grupo1.programacion_televisiva.model.TipoPrograma;

@Repository
public interface TipoProgramaRepository extends JpaRepository<TipoPrograma, UUID> {
}
