package com.example.demo.converter;

import com.example.demo.data.dto.DiscenteCompletoDTO;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Discente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class DiscenteConverter {

    @Autowired
    ModelMapper modelMapper;

    public DiscenteDTO toDto(Discente discente) {
        return modelMapper.map(discente, DiscenteDTO.class);
    }

    public DiscenteCompletoDTO toCompletoDto(Discente discente) {
        return modelMapper.map(discente, DiscenteCompletoDTO.class);
    }


    public Discente toEntity(DiscenteCompletoDTO dto) {
        return  modelMapper.map(dto, Discente.class);
    }
}
