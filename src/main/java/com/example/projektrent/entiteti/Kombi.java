package com.example.projektrent.entiteti;

import java.time.LocalDate;

public class Kombi extends Vozilo<Kombi>{
    private Integer ID;
    private LocalDate istekRegistracije;
    private String marka;
    private String model;
    private Integer snaga;
    private VrstaGoriva vrsta_goriva;
    private Integer brojSjedala;
    private static final Integer CIJENA = 90;

    public Kombi(Integer ID, String marka, String model, Integer snaga,
                     LocalDate istekRegistracije, VrstaGoriva vrsta_goriva, Integer brojSjedala) {
        super(ID, "kombi", marka, model);
        this.snaga=snaga;
        this.ID = ID;
        this.istekRegistracije = istekRegistracije;
        this.marka = marka;
        this.model = model;
        this.vrsta_goriva = vrsta_goriva;
        this.brojSjedala=brojSjedala;

        Vozilo.addKombiVozilo(this);
    }

    public Integer getBrojSjedala() {
        return brojSjedala;
    }

    public void setBrojSjedala(Integer brojSjedala) {
        this.brojSjedala = brojSjedala;
    }

    public Integer getID() {
        return ID;
    }

    public LocalDate getIstekRegistracije() {
        return istekRegistracije;
    }

    public void setIstekRegistracije(LocalDate istekRegistracije) {
        this.istekRegistracije = istekRegistracije;
    }

    public static Integer getCijena() {
        return CIJENA;
    }

    public VrstaGoriva getVrsta_goriva() {
        return vrsta_goriva;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    @Override
    public Integer getSnaga() {
        return this.snaga;
    }
}
