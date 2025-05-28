package com.example.demo.service;

import com.example.demo.converter.CorsoMapper;
import com.example.demo.converter.DiscenteConverter;
import com.example.demo.converter.DocenteConverter;
import com.example.demo.data.DTO.CorsoCompletoDTO;
import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.data.DTO.DocenteCompletoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public void save(CorsoCompletoDTO dto) {
        Corso corso = null;

        if (dto.getId_corso() != null) {
            corso = corsoRepository.findById(corso.getId_corso()).orElseThrow();
        } else {
            corso = new Corso();
        }

        Docente docente;
        if (dto.getDocente() != null) {
            // Verifica se il docente esiste basandosi su nome, cognome e data_di_nascita
            Optional<Docente> existingDocente = docenteRepository.findByNomeAndCognome
                    (
                    dto.getDocente().getNome(),
                    dto.getDocente().getCognome());

            if (existingDocente.isPresent()) {
                docente = existingDocente.get();
            } else {
                // Crea un nuovo docente
                docente = docenteConverter.toEntity((DocenteCompletoDTO) dto.getDocente());
                docente = docenteRepository.save(docente);
            }
        } else {
            throw new IllegalArgumentException("Il docente è obbligatorio");
        }

        Set<Discente> discenti = new HashSet<>();
        if (dto.getDiscenti() != null && !dto.getDiscenti().isEmpty()) {
            for (DiscenteDTO discenteDTO : dto.getDiscenti()) {
                // Verifica se il discente esiste basandosi su matricola
                Optional<Discente> existingDiscente = discenteRepository.findByNomeAndCognome(discenteDTO.getNome(), discenteDTO.getCognome());

                if (existingDiscente.isPresent()) {
                    discenti.add(existingDiscente.get());
                } else {
                    Discente discente = discenteConverter.toEntity(discenteDTO);
                    discente = discenteRepository.save(discente);
                    discenti.add(discente);
                }
            }
        }

        corsoMapper.updateEntityToDto(dto, corso, docente, discenti);
        corsoRepository.save(corso);

    }

    public void delete(Integer id_corso) {
        corsoRepository.deleteById(id_corso);
    }
}
