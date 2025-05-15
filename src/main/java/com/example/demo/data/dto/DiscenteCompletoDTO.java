package com.example.demo.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DiscenteCompletoDTO extends DiscenteDTO{

    @NotNull
    private Integer matricola;

    @NotNull
    private Integer età;

    @NotBlank
    private String città_di_residenza;

    public Integer getMatricola() {
        return matricola;
    }

    public void setMatricola(Integer matricola) {
        this.matricola = matricola;
    }

    public Integer getEtà() {
        return età;
    }

    public void setEtà(Integer età) {
        this.età = età;
    }

    public String getCittà_di_residenza() {
        return città_di_residenza;
    }

    public void setCittà_di_residenza(String città_di_residenza) {
        this.città_di_residenza = città_di_residenza;
    }
}
