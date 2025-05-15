package com.example.demo.converter;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CorsoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CorsoDTO toDto(Corso corso) {
        CorsoDTO dto = modelMapper.map(corso, CorsoDTO.class);
        if (corso.getDocente() != null) {
            dto.setId_docente(corso.getDocente().getId_docente());
        }
        if (corso.getDiscenti() != null) {
            Set<Integer> ids = corso.getDiscenti()
                    .stream()
                    .map(Discente::getId_discente)
                    .collect(Collectors.toSet());
            dto.setId_discenti(ids);
        }
        return dto;
    }

    //Attualmente inutilizzato, perchè non inserisce discenti e docenti
    public Corso toEntity(CorsoDTO dto) {
        return modelMapper.map(dto, Corso.class);
    }

    //Salvataggio toEntity fixato per discenti e docenti
    public void updateEntityToDto(CorsoDTO dto, Corso corso,
                                  Docente docente, Set<Discente> discenti) {
        corso.setNome(dto.getNome());
        corso.setAnno_accademico(dto.getAnno_accademico());
        corso.setDocente(docente);
        corso.setDiscenti(discenti);
    }
}
