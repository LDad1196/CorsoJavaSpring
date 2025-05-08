package com.example.demo.service;

import com.example.demo.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorsoService {

    @Autowired
    CorsoRepository corsoRepository;

    public List<Corso> findAll() {
        return corsoRepository.findAll();
    }

    public Corso findById(Integer id_corso) {
        return corsoRepository.findById(id_corso).orElse(null);
    }

    public Corso save(Corso corso) {
        return corsoRepository.save(corso);
    }

    public void delete(Integer id_corso) {corsoRepository.deleteById(id_corso);}

}
