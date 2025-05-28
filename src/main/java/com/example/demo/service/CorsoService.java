package com.example.demo.service;

import com.example.demo.converter.CorsoMapper;
import com.example.demo.converter.DiscenteConverter;
import com.example.demo.converter.DocenteConverter;
import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CorsoService {

    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private DocenteConverter docenteConverter;

    @Autowired
    private CorsoMapper corsoMapper;

    @Autowired
    private DiscenteConverter discenteConverter;

    public List<CorsoDTO> findAll() {
        return corsoRepository.findAll()
                .stream()
                .map(corsoMapper::toDto)
                .toList();
    }

    public CorsoDTO findById(Integer id_corso) {
        return corsoRepository.findById(id_corso)
                .map(corsoMapper::toDto)
                .orElse(null);
    }

    public CorsoDTO save(CorsoDTO corsoDTO) {
        Corso corso = new Corso();

        // Imposta i valori di base
        corso.setNome(corsoDTO.getNome());
        corso.setAnno_accademico(corsoDTO.getAnno_accademico());

        // Gestione del docente
        if (corsoDTO.getDocente() != null) {
            String nomeDocente = corsoDTO.getDocente().getNome();
            String cognomeDocente = corsoDTO.getDocente().getCognome();

            List<Docente> docenti = docenteRepository.findByNomeAndCognome(nomeDocente, cognomeDocente);

            Docente docente;
            if (!docenti.isEmpty()) {
                docente = docenti.get(0); // Prendi il primo docente trovato
            } else {
                docente = new Docente(); // Crea un nuovo docente
                docente.setNome(nomeDocente);
                docente.setCognome(cognomeDocente);
                docente = docenteRepository.save(docente);
            }
            corso.setDocente(docente);
        }

        // Gestione dei discenti
        if (corsoDTO.getDiscenti() != null && !corsoDTO.getDiscenti().isEmpty()) {
            Set<Discente> discenti = corsoDTO.getDiscenti().stream().map(discenteDTO -> {
                List<Discente> discentiTrovati = discenteRepository.findByNomeAndCognome(
                        discenteDTO.getNome(), discenteDTO.getCognome()
                );

                if (!discentiTrovati.isEmpty()) {
                    return discentiTrovati.get(0); // Prendi il primo discente trovato
                } else {
                    Discente nuovoDiscente = new Discente(); // Crea un nuovo discente
                    nuovoDiscente.setNome(discenteDTO.getNome());
                    nuovoDiscente.setCognome(discenteDTO.getCognome());
                    return discenteRepository.save(nuovoDiscente);
                }
            }).collect(Collectors.toSet());

            corso.setDiscenti(discenti);
        } else {
            corso.setDiscenti(Set.of()); // Nessun discente associato
        }

        // Salva e restituisci il DTO
        Corso corsoSalvato = corsoRepository.save(corso);
        return corsoMapper.toDto(corsoSalvato);
    }


    public CorsoDTO update(Integer id_corso, CorsoDTO corsoDTO) {
        Corso corso = corsoRepository.findById(id_corso)
                .orElseThrow(() -> new IllegalArgumentException("Corso non trovato con ID: " + id_corso));

        // Aggiornamento dei valori base
        if (corsoDTO.getNome() != null) {
            corso.setNome(corsoDTO.getNome());
        }
        if (corsoDTO.getAnno_accademico() != null) {
            corso.setAnno_accademico(corsoDTO.getAnno_accademico());
        }

        // Aggiornamento del docente
        if (corsoDTO.getDocente() != null) {
            String nomeDocente = corsoDTO.getDocente().getNome();
            String cognomeDocente = corsoDTO.getDocente().getCognome();

            List<Docente> docenti = docenteRepository.findByNomeAndCognome(nomeDocente, cognomeDocente);

            Docente docente;
            if (!docenti.isEmpty()) {
                docente = docenti.get(0); // Prendi il primo docente trovato
            } else {
                docente = new Docente(); // Crea un nuovo docente
                docente.setNome(nomeDocente);
                docente.setCognome(cognomeDocente);
                docente = docenteRepository.save(docente);
            }
            corso.setDocente(docente);
        }

        // Aggiornamento dei discenti
        if (corsoDTO.getDiscenti() != null && !corsoDTO.getDiscenti().isEmpty()) {
            Set<Discente> discenti = corsoDTO.getDiscenti().stream().map(discenteDTO -> {
                List<Discente> discentiTrovati = discenteRepository.findByNomeAndCognome(
                        discenteDTO.getNome(), discenteDTO.getCognome()
                );

                if (!discentiTrovati.isEmpty()) {
                    return discentiTrovati.get(0); // Prendi il primo discente trovato
                } else {
                    Discente nuovoDiscente = new Discente(); // Crea un nuovo discente
                    nuovoDiscente.setNome(discenteDTO.getNome());
                    nuovoDiscente.setCognome(discenteDTO.getCognome());
                    return discenteRepository.save(nuovoDiscente);
                }
            }).collect(Collectors.toSet());

            corso.setDiscenti(discenti);
        } else {
            corso.setDiscenti(Set.of()); // Nessun discente specificato
        }

        // Salva e restituisci il DTO aggiornato
        Corso corsoAggiornato = corsoRepository.save(corso);
        return corsoMapper.toDto(corsoAggiornato);
    }


    public void delete(Integer id_corso) {
        corsoRepository.deleteById(id_corso);
    }
}
