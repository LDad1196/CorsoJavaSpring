package com.example.demo.service;

import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {

    @Autowired
    DocenteRepository docenteRepository;

    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }

    public Docente findById(Integer id_docente) {
        return docenteRepository.findById(id_docente).orElse(null);
    }

    public Docente save(Docente docente) {
        return docenteRepository.save(docente);
    }

    public void delete(Integer id_docente) {
        docenteRepository.deleteById(id_docente);
    }

    public List<Docente> cercaPerNome(String nome) {
        return docenteRepository.cercaPerNome(nome);
    }

}
