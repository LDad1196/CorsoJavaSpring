package com.example.demo.repository;

import com.example.demo.data.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DiscenteRepository extends JpaRepository<Discente, Integer> {
    @Query("SELECT s FROM Discente s WHERE LOWER(s.nome) LIKE LOWER (CONCAT ('%', :nome, '%'))")
    List<Discente> cercaPerNome(@Param("nome") String nome);

    @Query("SELECT d FROM Discente d WHERE d.nome = :nome AND d.cognome = :cognome")
    List<Discente> findByNomeAndCognome(@Param("nome") String nome, @Param("cognome") String cognome);

}
