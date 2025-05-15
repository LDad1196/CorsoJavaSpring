package com.example.demo.data.dto;

import jakarta.validation.constraints.NotBlank;

public class DocenteCompletoDTO extends DocenteDTO {

    @NotBlank
    private String data_di_nascita;

    public String getData_di_nascita() {
        return data_di_nascita;
    }

    public void setData_di_nascita(String data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }
}
