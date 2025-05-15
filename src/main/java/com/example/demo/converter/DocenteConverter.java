package com.example.demo.converter;

import com.example.demo.data.dto.DocenteCompletoDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Docente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocenteConverter {

    @Autowired
    ModelMapper modelMapper;

    public DocenteDTO toDto(Docente docente) {
        if (docente == null) return null;
        DocenteDTO dto = new DocenteDTO();
        dto.setId_docente(docente.getId_docente());
        dto.setNome(docente.getNome());
        dto.setCognome(docente.getCognome());
        return dto;
    }

    public DocenteCompletoDTO toCompletoDto(Docente docente) {
        if (docente == null) return null;
        DocenteCompletoDTO dto = new DocenteCompletoDTO();
        dto.setId_docente(docente.getId_docente());
        dto.setNome(docente.getNome());
        dto.setCognome(docente.getCognome());
        dto.setData_di_nascita(docente.getData_di_nascita());
        return dto;
    }

    public Docente toEntity(DocenteCompletoDTO dto) {
        if (dto == null) return null;
        Docente docente = new Docente();
        docente.setId_docente(dto.getId_docente());
        docente.setNome(dto.getNome());
        docente.setCognome(dto.getCognome());
        docente.setData_di_nascita(dto.getData_di_nascita());
        return docente;
    }
}
