package com.example.demo.data.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class CorsoDTO {

    private Integer id_corso;

    @NotBlank
    private String nome;

    @NotBlank
    private String anno_accademico;

    private Integer id_docente;

    private Set<Integer> id_discenti = new HashSet<>();

    public String getAnno_accademico() {
        return anno_accademico;
    }

    public void setAnno_accademico(String anno_accademico) {
        this.anno_accademico = anno_accademico;
    }

    public Integer getId_corso() {
        return id_corso;
    }

    public Set<Integer> getId_discenti() {
        return id_discenti;
    }

    public void setId_discenti(Set<Integer> id_discenti) {
        this.id_discenti = id_discenti;
    }

    public void setId_corso(Integer id_corso) {
        this.id_corso = id_corso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId_docente() {
        return id_docente;
    }

    public void setId_docente(Integer id_docente) {
        this.id_docente = id_docente;
    }

}
