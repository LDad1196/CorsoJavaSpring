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

    @Column
    private Integer matricola;

    @Column
    private Integer eta;

    @Column
    private String citta;

    public Discente() {}

    public Discente(String nome, String cognome, Integer matricola, Integer eta, String citta) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.eta = eta;
        this.citta = citta;
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

    public Integer getEta() {
        return eta;
    }

    public String getCitta() {
        return citta;
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

    public void setEta(Integer eta) {
        this.eta = eta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    @ManyToMany(mappedBy = "discenti")
    private Set<Corso> corsi = new HashSet<>();

    public Set<Corso> getCorsi() {
        return corsi;
    }
    
}

