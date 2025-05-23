package com.example.demo.service;

import com.example.demo.converter.DiscenteConverter;
import com.example.demo.data.DTO.DiscenteCompletoDTO;
import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DiscenteService {

    @Autowired
    DiscenteRepository discenteRepository;

    @Autowired
    DiscenteConverter discenteConverter;

    @Autowired
    CorsoRepository corsoRepository;

    public List<DiscenteDTO> findAllSintetico() {
        return discenteRepository.findAll()
                .stream()
                .map(discenteConverter::toDto)
                .toList();
    }

    public DiscenteDTO findById(Integer id_discente) {
        return discenteRepository.findById(id_discente)
                .map(discenteConverter::toCompletoDto)
                .orElse(null);
    }

    public void save(DiscenteCompletoDTO dto) {
        discenteRepository.save(discenteConverter.toEntity(dto));
    }

    public List<DiscenteDTO> cercaPerNome(String nome) {
        return discenteRepository.cercaPerNome(nome)
                .stream()
                .map(discenteConverter::toDto)
                .toList();
    }



    public void deleteByIdConRimozioneDaCorsi(Integer id_discente) {
        Discente discente = discenteRepository.findById(id_discente)
                .orElseThrow(() -> new IllegalCallerException("ID discente non valido: " + id_discente));
        for (Corso corso : new HashSet<>(discente.getCorsi())) {
            corso.getDiscenti().remove(discente);
            corsoRepository.save(corso);
        }
        discenteRepository.delete(discente);
    }
}
