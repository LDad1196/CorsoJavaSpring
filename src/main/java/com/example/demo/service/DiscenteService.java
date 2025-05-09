package com.example.demo.service;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class DiscenteService {

    @Autowired
    DiscenteRepository discenteRepository;

    @Autowired
    CorsoRepository corsoRepository;

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

    public void deleteByIdConRimozioneDaCorsi(Integer id_discente) {
        Discente discente = discenteRepository.findById(id_discente).orElseThrow(() -> new IllegalCallerException("ID discente non valido: " + id_discente));
        for (Corso corso : new HashSet<>(discente.getCorsi())) {
            corso.getDiscenti().remove(discente);
            corsoRepository.save(corso);
        }
        discenteRepository.delete(discente);
    }
}
