package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import javax.print.Doc;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "corso")
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_corso;

    @Column(nullable = false)
    @NotBlank(message = "Inserisci il nome del corso")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "Inserisci l'anno accedemico")
    private String anno_accademico;

    public Corso() {}

    public Corso(String nome, String anno_accademico, Integer id_corso) {
        this.nome = nome;
    }

    public void setId_corso(Integer id_corso) {
        this.id_corso = id_corso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId_corso() {
        return id_corso;
    }

    public String getNome() {
        return nome;
    }

    public void setAnno_accademico(String anno_accademico) {
        this.anno_accademico = anno_accademico;
    }

    public String getAnno_accademico() {
        return anno_accademico;
    }

    @ManyToOne
    @JoinColumn(name = "id_docente", nullable = true)
    private Docente docente;

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Docente getDocente() {
        return docente;
    }

    @ManyToMany
    @JoinTable(
            name = "corsi_discenti",
            joinColumns = @JoinColumn(name = "id_corso"),
            inverseJoinColumns = @JoinColumn(name = "id_discente")
    )
    private Set<Discente> discenti = new HashSet<>();

    public Set<Discente> getDiscenti() {
        return discenti;
    }

    public void setDiscenti(Set<Discente> discenti) {
        this.discenti = discenti;
    }
}
