package com.example.demo.service;

import com.example.demo.converter.DiscenteConverter;
import com.example.demo.data.DTO.DiscenteCompletoDTO;
import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
public class DiscenteService {

    @Autowired
    DiscenteRepository discenteRepository;

    @Autowired
    DiscenteConverter discenteConverter;

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

    public DiscenteCompletoDTO save(DiscenteCompletoDTO dto) {
        Discente discente = discenteConverter.toEntity(dto);
        discente = discenteRepository.save(discente);
        return discenteConverter.toCompletoDto(discente);
    }

    public DiscenteCompletoDTO update(Integer id_discente, DiscenteCompletoDTO dto) {
        Discente discente = discenteRepository.findById(id_discente)
                .orElseThrow(() -> new IllegalCallerException("ID discente non valido: " + id_discente));
        if (dto.getNome() != null) {
            discente.setNome(dto.getNome());
        }
        if (dto.getCognome() != null) {
            discente.setCognome(dto.getCognome());
        }
        if (dto.getMatricola() != null) {
            discente.setMatricola(dto.getMatricola());
        }
        if(dto.getEta() != null) {
            discente.setEtà(dto.getEta());
        }
        if(dto.getCitta() != null) {
            discente.setCittà_di_residenza(dto.getCitta());
        }
        discente = discenteRepository.save(discente);
        return discenteConverter.toCompletoDto(discente);
    }

    public List<DiscenteDTO> cercaPerNome(String nome) {
        return discenteRepository.cercaPerNome(nome)
                .stream()
                .map(discenteConverter::toDto)
                .toList();
    }



    public void deleteById(Integer id_discente) {
        Discente discente = discenteRepository.findById(id_discente)
                .orElseThrow(() -> new IllegalCallerException("ID discente non valido: " + id_discente));
        discenteRepository.delete(discente);
    }
}
