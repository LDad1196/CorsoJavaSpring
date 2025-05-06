package com.example.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "studente")
public class Studente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_studente;

    @Column(nullable = false)
    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @Column(nullable = false)
    @NotNull(message = "La matricola è obbligatoria")
    private Integer matricola;

    @Column(nullable = false)
    @NotNull(message = "L'età è obbligatoria!")
    private Integer età;

    @Column(nullable = false)
    @NotBlank(message = "La città di residenza è un campo obbligatorio!")
    private String città_di_residenza;

    public Studente() {}

    public Studente(String nome, String cognome, Integer matricola, Integer età, String città_di_residenza) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.età = età;
        this.città_di_residenza = città_di_residenza;
    }

    public Integer getId_studente() {
        return id_studente;
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

    public void setId_studente(Integer id_studente) {
        this.id_studente = id_studente;
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
}

