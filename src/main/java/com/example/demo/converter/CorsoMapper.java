package com.example.demo.converter;

import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CorsoMapper {

    @Autowired
    DocenteConverter docenteConverter;

    @Autowired
    DiscenteConverter discenteConverter;

    @Mapping(target = "docente", expression = "java(docenteConverter.toDto(corso.getDocente()))")
    @Mapping(target = "discenti", expression = "java(mapDiscentiToDTO(corso.getDiscenti()))")
    public abstract CorsoDTO toDto(Corso corso);

    @Mapping(target = "docente", ignore = true)
    @Mapping(target = "discenti", ignore = true)
    public abstract Corso toEntity(CorsoDTO dto);

    protected Set<DiscenteDTO> mapDiscentiToDTO(Set<Discente> discenti) {
        if (discenti == null) {
            return new HashSet<>();
        }
        return discenti.stream()
                .map(discenteConverter::toDto)
                .collect(Collectors.toSet());
    }


    public void updateEntityToDto(CorsoDTO dto, @MappingTarget Corso corso,
                                  Docente docente, Set<Discente> discenti) {
        corso.setNome(dto.getNome());
        corso.setAnno_accademico(dto.getAnno_accademico());
        corso.setDocente(docente);
        corso.setDiscenti(discenti);
    }
}
