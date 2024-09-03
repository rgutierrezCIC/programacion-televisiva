package es.cic.grupo1.programacion_televisiva.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo1.programacion_televisiva.model.Programa;
import es.cic.grupo1.programacion_televisiva.repository.ProgramaRepository;
@Service
public class ProgramaService {
    @Autowired
    private ProgramaRepository programaRepository;


    
    public Programa savePrograma(Programa programa) {
        return programaRepository.save(programa);
    }

  
    public List<Programa> getAllProgramas() {
        return programaRepository.findAll();
    }


    public Optional<Programa> getProgramaById(UUID id) {
        return programaRepository.findById(id);
    }


    public void deletePrograma(UUID id) {
        programaRepository.deleteById(id);
    }

    public Programa updatePrograma(Programa programa) {
        return programaRepository.save(programa);
    }
}