package com.example.demo.data.DTO;

import jakarta.validation.constraints.NotBlank;

public class DocenteCompletoDTO extends DocenteDTO {

    private Integer id_docente;

    private String data_di_nascita;

    public Integer getId_docente() {
        return id_docente;
    }

    public void setId_docente(Integer id_docente) {
        this.id_docente = id_docente;
    }

    public String getData_di_nascita() {
        return data_di_nascita;
    }

    public void setData_di_nascita(String data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }

}
