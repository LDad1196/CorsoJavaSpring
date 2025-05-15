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
        return modelMapper.map(docente, DocenteDTO.class);
    }

    public DocenteCompletoDTO toCompletoDto(Docente docente) {
        return modelMapper.map(docente, DocenteCompletoDTO.class);
    }

    public Docente toEntity(DocenteCompletoDTO dto) {
        return modelMapper.map(dto, Docente.class);
    }
}
