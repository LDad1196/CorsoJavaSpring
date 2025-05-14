package com.example.demo.converter;

import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import org.mapstruct.*;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class CorsoMapper {

    @Mapping(source = "docente.id_docente", target = "id_docente")
    @Mapping(target = "id_discenti", expression = "java(extractId_discenti(corso))")
    public abstract CorsoDTO toDto(Corso corso);

    @Mapping(target = "docente", ignore = true)
    @Mapping(target = "discenti", ignore = true)
    public abstract Corso toEntity(CorsoDTO dto);

    protected Set<Integer> extractId_discenti(Corso corso) {
        Set<Integer> ids = new HashSet<>();
        for (Discente discente : corso.getDiscenti()) {
            ids.add(discente.getId_discente());
        }
        return ids;
    }

    public void updateEntityToDto(CorsoDTO dto, @MappingTarget Corso corso,
                                  Docente docente, Set<Discente> discenti) {
        corso.setNome(dto.getNome());
        corso.setAnno_accademico(dto.getAnno_accademico());
        corso.setDocente(docente);
        corso.setDiscenti(discenti);
    }
}
