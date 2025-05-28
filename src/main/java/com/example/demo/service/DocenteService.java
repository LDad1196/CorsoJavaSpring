package com.example.demo.service;

import com.example.demo.converter.CorsoMapper;
import com.example.demo.converter.DocenteConverter;
import com.example.demo.data.DTO.DocenteCompletoDTO;
import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.data.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DocenteService {

    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    DocenteConverter docenteConverter;

    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    CorsoMapper corsoMapper;

    public List<DocenteDTO> findAllSintetico() {
        return docenteRepository.findAll()
                .stream()
                .map(docenteConverter::toDto)
                .toList();
    }

    public DocenteDTO findById(Integer id_docente) {
        return docenteRepository.findById(id_docente)
                .map(docenteConverter::toDto)
                .orElse(null);
    }

    public DocenteCompletoDTO save(DocenteCompletoDTO dto) {
        Docente entita = docenteConverter.toEntity(dto);
        entita = docenteRepository.save(entita);
        return docenteConverter.toCompletoDto(entita);
    }


    public DocenteCompletoDTO update(Integer id_docente, DocenteCompletoDTO dto) {
        // Recupero del docente esistente.
        Docente docente = docenteRepository.findById(id_docente)
                .orElseThrow(() -> new IllegalArgumentException("Docente non trovato"));
        if (dto.getNome() != null) {
            docente.setNome(dto.getNome());
        }
        if (dto.getCognome() != null) {
            docente.setCognome(dto.getCognome());
        }
        if (dto.getData_di_nascita() != null) {
            docente.setData_di_nascita(dto.getData_di_nascita());
        }
        docente = docenteRepository.save(docente);
        return docenteConverter.toCompletoDto(docente);
    }


    public List<DocenteDTO> cercaPerNome(String nome) {
        return docenteRepository.cercaPerNome(nome)
                .stream()
                .map(docenteConverter::toDto)
                .toList();
    }


    public void deleteById(Integer id_docente) {
        Docente docente = docenteRepository.findById(id_docente)
                .orElseThrow(() -> new IllegalArgumentException("Docente non trovato"));
        for (Corso corso : docente.getCorsi()) {
            corso.setDocente(null);
            corsoRepository.save(corso);
        }
        docenteRepository.delete(docente);
    }
}
