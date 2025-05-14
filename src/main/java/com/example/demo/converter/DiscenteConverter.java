package com.example.demo.converter;

import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.data.entity.Discente;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class DiscenteConverter {

    public DiscenteDTO toDto(Discente discente) {
        if (discente == null) return null;
        DiscenteDTO dto = new DiscenteDTO();
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
        discente.setId_discente(dto.getId_discente());
        discente.setNome(dto.getNome());
        discente.setCognome(dto.getCognome());
        discente.setMatricola(dto.getMatricola());
        discente.setEtà(dto.getEta());
        discente.setCittà_di_residenza(dto.getCitta());
        return discente;
    }
}
