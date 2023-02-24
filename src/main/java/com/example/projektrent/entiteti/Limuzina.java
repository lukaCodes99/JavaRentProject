package com.example.projektrent.entiteti;

import java.time.LocalDate;

public class Limuzina extends Vozilo<Limuzina>{

    private Integer ID;
    private LocalDate istekRegistracije;
    private String marka;
    private String model;
    private Integer snaga;
    private VrstaGoriva vrsta_goriva;

    private static final Integer CIJENA = 70;

    public Limuzina(Integer ID, String marka, String model, Integer snaga,
                 LocalDate istekRegistracije, VrstaGoriva vrsta_goriva) {
        super(ID, "limuzina", marka, model);
        this.snaga=snaga;
        this.ID = ID;
        this.istekRegistracije = istekRegistracije;
        this.marka = marka;
        this.model = model;
        this.vrsta_goriva = vrsta_goriva;
        Vozilo.addLimuzinaVozilo(this);
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
