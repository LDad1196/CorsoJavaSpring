package com.example.demo.service;

import com.example.demo.converter.CorsoMapper;
import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import jakarta.persistence.SecondaryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CorsoService {

    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private CorsoMapper corsoMapper;

    public List<CorsoDTO> findAll() {
        return corsoRepository.findAll()
                .stream()
                .map(corsoMapper::toDto)
                .toList();
    }

    public CorsoDTO findById(Integer id_corso) {
        return corsoRepository.findById(id_corso)
                .map(corsoMapper::toDto)
                .orElse(null);
    }

    public void save(CorsoDTO dto) {
        Corso corso;

        if (dto.getId_corso() != null) {
            corso = corsoRepository.findById(dto.getId_corso()).orElseThrow();
        } else {
            corso = new Corso();
        }

        Docente docente = docenteRepository.findById(dto.getId_docente()).orElseThrow();

        Set<Discente> discenti = new HashSet<>();
        for (Integer id_discente : dto.getId_discenti()) {
            discenteRepository.findById(id_discente).ifPresent(discenti::add);
        }

        corsoMapper.updateEntityToDto(dto, corso, docente, discenti);
        corsoRepository.save(corso);
    }

    public void delete(Integer id_corso) {
        corsoRepository.deleteById(id_corso);
    }
}
