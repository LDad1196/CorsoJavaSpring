package com.example.demo.repository;

import com.example.demo.entity.Studente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudenteRepository extends JpaRepository<Studente, Integer> {
    @Query("SELECT s FROM Studente s WHERE LOWER(s.nome) LIKE LOWER (CONCAT ('%', :nome, '%'))")
    List<Studente> cercaPerNome(@Param("nome") String nome);
}
