package com.example.demo.converter;

import com.example.demo.data.DTO.DiscenteCompletoDTO;
import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.data.entity.Discente;
import org.springframework.stereotype.Component;

@Component
public class DiscenteConverter {

    public DiscenteDTO toDto(Discente discente) {
        if (discente == null) return null;
        DiscenteDTO dto = new DiscenteDTO();
        dto.setNome(discente.getNome());
        dto.setCognome(discente.getCognome());
        return dto;
    }

    public DiscenteCompletoDTO toCompletoDto(Discente discente) {
        if (discente == null) return null;
        DiscenteCompletoDTO dto = new DiscenteCompletoDTO();
        dto.setId_discente(discente.getId_discente());
        dto.setNome(discente.getNome());
        dto.setCognome(discente.getCognome());
        dto.setMatricola(discente.getMatricola());
        dto.setEta(discente.getEtà());
        dto.setCitta(discente.getCittà_di_residenza());
        return dto;
    }


    public Discente toEntity(DiscenteDTO dto) {
        if (dto == null) return null;
        Discente discente = new Discente();
        discente.setNome(dto.getNome());
        discente.setCognome(dto.getCognome());
        return discente;
    }
}
