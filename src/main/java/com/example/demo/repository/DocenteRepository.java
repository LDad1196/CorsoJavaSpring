package com.example.demo.repository;

import com.example.demo.data.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    @Query("SELECT d FROM Docente d WHERE LOWER(d.nome) LIKE LOWER (CONCAT ('%', :nome, '%'))")
    List<Docente> cercaPerNome(@Param("nome") String nome);
}
