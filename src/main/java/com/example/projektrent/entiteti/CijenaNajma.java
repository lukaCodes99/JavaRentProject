package com.example.projektrent.entiteti;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CijenaNajma<T extends Vozilo, S extends LocalDate> {
    //može se iskoristiti ako želim raditi najam soba ili nesto slicno T extends Vozilo & Soba
    //localdatetime ako zelim iznajmljivati nesto po satu i predati cijenu po satu recimo za ferrarija
    private T vozilo;
    private S pocetak;
    private S kraj;
    private Double cijena;

    public CijenaNajma(T vozilo, S pocetak, S kraj, Double cijena) {
        this.vozilo = vozilo;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.cijena = cijena;
    }

    public Double izracunajCijenu() {
        long brojDana = ChronoUnit.DAYS.between(pocetak, kraj);

        Double ukupnaCijena = brojDana * cijena;
        if (brojDana > 10) {
            ukupnaCijena = ukupnaCijena * 0.8;
        }
        return ukupnaCijena;
    }

    public T getVozilo() {
        return vozilo;
    }

    public S getPocetak() {
        return pocetak;
    }

    public S getKraj() {
        return kraj;
    }

    public Double getCijena() {
        return cijena;
    }
}

