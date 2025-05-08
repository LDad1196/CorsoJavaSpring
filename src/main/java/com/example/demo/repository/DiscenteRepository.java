package com.example.demo.repository;

import com.example.demo.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscenteRepository extends JpaRepository<Discente, Integer> {
    @Query("SELECT s FROM Discente s WHERE LOWER(s.nome) LIKE LOWER (CONCAT ('%', :nome, '%'))")
    List<Discente> cercaPerNome(@Param("nome") String nome);
}
