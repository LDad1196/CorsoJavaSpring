package com.example.demo.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "discente")
public class Discente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_discente;

    @Column
    private String nome;

    @Column
    private String cognome;

    @Column(nullable = false)
    private Integer matricola;

    @Column(nullable = false)
    private Integer età;

    @Column(nullable = false)
    private String città_di_residenza;

    public Discente() {}

    public Discente(String nome, String cognome, Integer matricola, Integer età, String città_di_residenza) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.età = età;
        this.città_di_residenza = città_di_residenza;
    }

    public Integer getId_discente() {
        return id_discente;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Integer getMatricola() {
        return matricola;
    }

    public Integer getEtà() {
        return età;
    }

    public String getCittà_di_residenza() {
        return città_di_residenza;
    }

    public void setId_discente(Integer id_discente) {
        this.id_discente = id_discente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setMatricola(Integer matricola) {
        this.matricola = matricola;
    }

    public void setEtà(Integer età) {
        this.età = età;
    }

    public void setCittà_di_residenza(String città_di_residenza) {
        this.città_di_residenza = città_di_residenza;
    }

    @ManyToMany(mappedBy = "discenti")
    private Set<Corso> corsi = new HashSet<>();

    public Set<Corso> getCorsi() {
        return corsi;
    }
}

