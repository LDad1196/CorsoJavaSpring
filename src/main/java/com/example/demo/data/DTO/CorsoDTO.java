package com.example.demo.data.DTO;

import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class CorsoDTO {

    private Integer id_corso;

    @NotBlank
    private String nome;

    @NotBlank
    private String anno_accademico;

    private DocenteDTO docente;

    private Set<DiscenteDTO> discenti = new HashSet<>();

    public String getAnno_accademico() {
        return anno_accademico;
    }

    public void setAnno_accademico(String anno_accademico) {
        this.anno_accademico = anno_accademico;
    }

    public Integer getId_corso() {
        return id_corso;
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

    public DocenteDTO getDocente() {
        return docente;
    }

    public void setDocente(DocenteDTO docente) {
        this.docente = docente;
    }

    public Set<DiscenteDTO> getDiscenti() {
        return discenti;
    }

    public void setDiscenti(Set<DiscenteDTO> discenti) {
        this.discenti = discenti;
    }


}
