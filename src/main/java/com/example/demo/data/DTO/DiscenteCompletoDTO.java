package com.example.demo.data.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DiscenteCompletoDTO extends DiscenteDTO{

    private Integer id_discente;

    public Integer getId_discente() {
        return id_discente;
    }

    public void setId_discente(Integer id_discente) {
        this.id_discente = id_discente;
    }


}
