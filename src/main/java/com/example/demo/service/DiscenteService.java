package com.example.demo.service;

import com.example.demo.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscenteService {

    @Autowired
    DiscenteRepository discenteRepository;

    public List<Discente> findAll() {
        return discenteRepository.findAll();
    }

    public Discente findById(Integer id_discente) {
        return discenteRepository.findById(id_discente).orElse(null);
    }

    public Discente save(Discente discente) {
        return discenteRepository.save(discente);
    }

    public void delete(Integer id_discente) {
        discenteRepository.deleteById(id_discente);
    }

    public List<Discente> cercaPerNome(String nome) {
        return discenteRepository.cercaPerNome(nome);
    }
}
