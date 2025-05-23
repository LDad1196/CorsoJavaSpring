package com.example.demo.data.DTO;

import jakarta.validation.constraints.NotBlank;

public class DocenteCompletoDTO extends DocenteDTO {

    private Integer id_docente;

    public Integer getId_docente() {
        return id_docente;
    }

    public void setId_docente(Integer id_docente) {
        this.id_docente = id_docente;
    }
}
