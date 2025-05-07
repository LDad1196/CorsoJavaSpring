package com.example.demo.service;

import com.example.demo.entity.Studente;
import com.example.demo.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudenteService {

    @Autowired
    StudenteRepository studenteRepository;

    public List<Studente> findAll() {
        return studenteRepository.findAll();
    }

    public Studente findById(Integer id_studente) {
        return studenteRepository.findById(id_studente).orElse(null);
    }

    public Studente save(Studente studente) {
        return studenteRepository.save(studente);
    }

    public void delete(Integer id_studente) {
        studenteRepository.deleteById(id_studente);
    }

    public List<Studente> cercaPerNome(String nome) {
        return studenteRepository.cercaPerNome(nome);
    }
}
