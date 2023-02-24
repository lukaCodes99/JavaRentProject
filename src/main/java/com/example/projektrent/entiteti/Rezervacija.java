package com.example.projektrent.entiteti;

import java.time.LocalDate;

public class Rezervacija {

    private LocalDate pocetak_rezervacije;
    private LocalDate kraj_rezervacije;
    private Integer idVozila;


    public Rezervacija(LocalDate pocetak_rezervacije, LocalDate kraj_rezervacije, Integer idVozila) {
        this.pocetak_rezervacije = pocetak_rezervacije;
        this.kraj_rezervacije = kraj_rezervacije;
        this.idVozila = idVozila;
    }

    public LocalDate getPocetak_rezervacije() {
        return pocetak_rezervacije;
    }

    public void setPocetak_rezervacije(LocalDate pocetak_rezervacije) {
        this.pocetak_rezervacije = pocetak_rezervacije;
    }

    public LocalDate getKraj_rezervacije() {
        return kraj_rezervacije;
    }

    public void setKraj_rezervacije(LocalDate kraj_rezervacije) {
        this.kraj_rezervacije = kraj_rezervacije;
    }

    public Integer getIdVozila() {
        return idVozila;
    }

    public void setIdVozila(Integer idVozila) {
        this.idVozila = idVozila;
    }


}
