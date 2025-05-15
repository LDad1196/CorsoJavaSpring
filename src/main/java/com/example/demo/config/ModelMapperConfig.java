package com.example.demo.config;

import com.example.demo.data.dto.DocenteCompletoDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Docente;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.*;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Docente, DocenteDTO> docenteSintetico = modelMapper.createTypeMap(Docente.class, DocenteDTO.class);
        docenteSintetico.addMappings(mapper -> {
            mapper.map(Docente::getId_docente, DocenteDTO::setId_docente);
            mapper.map(Docente::getNome, DocenteDTO::setNome);
            mapper.map(Docente::getCognome, DocenteDTO::setCognome);
        });

        TypeMap<Docente, DocenteCompletoDTO> docenteCompleto = modelMapper.createTypeMap(Docente.class, DocenteCompletoDTO.class);
        docenteCompleto.addMappings(mapper -> {
            mapper.map(Docente::getId_docente, DocenteCompletoDTO::setId_docente);
            mapper.map(Docente::getNome, DocenteCompletoDTO::setNome);
            mapper.map(Docente::getCognome, DocenteCompletoDTO::setCognome);
            mapper.map(Docente::getData_di_nascita, DocenteCompletoDTO::setData_di_nascita);
        });

        modelMapper.createTypeMap(DocenteDTO.class, Docente.class);
        modelMapper.createTypeMap(DocenteCompletoDTO.class, Docente.class);

        return modelMapper;
    }
}
