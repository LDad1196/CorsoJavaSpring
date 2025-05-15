package com.example.demo.service;

import com.example.demo.converter.DocenteConverter;
import com.example.demo.data.dto.DocenteCompletoDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.data.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import org.modelmapper.ModelMapper;
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
    ModelMapper modelMapper;

    public DocenteDTO toDtoSintetico(Docente docente) {
        return modelMapper.map(docente, DocenteDTO.class);
    }

    public DocenteCompletoDTO toDtoCompleto(Docente docente) {
        return modelMapper.map(docente, DocenteCompletoDTO.class);
    }

    public Docente toEntity(DocenteDTO dto) {
        return modelMapper.map(dto, Docente.class);
    }

    public Docente toEntity(DocenteCompletoDTO dto) {
        return modelMapper.map(dto, Docente.class);
    }

    public List<DocenteDTO> findAllSintetico() {
        return docenteRepository.findAll()
                .stream()
                .map(this::toDtoSintetico)
                .toList();
    }

    public DocenteCompletoDTO findByIdCompleto(Integer id_docente) {
        return toDtoCompleto(docenteRepository.findById(id_docente).orElseThrow(() -> new IllegalArgumentException("Docente non trovato!")));
    }

    public void save(DocenteCompletoDTO dto) {
        docenteRepository.save(toEntity(dto));
    }

    public List<DocenteDTO> cercaPerNome(String nome) {
        return docenteRepository.cercaPerNome(nome)
                .stream()
                .map(this::toDtoSintetico)
                .toList();
    }

    public Map<Integer, String> getMappaDocenti() {
        return findAllSintetico().stream()
                .collect(Collectors.toMap(
                        DocenteDTO::getId_docente,
                        dto -> dto.getNome() + " " + dto.getCognome()
                ));
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
