package com.example.demo.data.DTO;



public class DiscenteCompletoDTO extends DiscenteDTO{

    private Integer id_discente;

    private Integer matricola;

    private Integer eta;

    private String citta;

    public Integer getId_discente() {
        return id_discente;
    }

    public void setId_discente(Integer id_discente) {
        this.id_discente = id_discente;
    }

    public Integer getMatricola() {
        return matricola;
    }

    public void setMatricola(Integer matricola) {
        this.matricola = matricola;
    }

    public Integer getEta() {
        return eta;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }
}
