package com.example.projektrent.entiteti;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class Hatchback extends Vozilo<Hatchback> implements Serializable {

    private Integer ID;

    private LocalDate istekRegistracije;
    private String marka;
    private String model;
    private Integer snaga;
    private VrstaGoriva vrsta_goriva;
    private static final Integer CIJENA = 40;

    public Hatchback(Integer ID, String marka, String model, Integer snaga,
                     LocalDate istekRegistracije, VrstaGoriva vrsta_goriva) {
        super(ID, "hatchback", marka, model);
        this.snaga=snaga;
        this.ID = ID;
        this.istekRegistracije = istekRegistracije;
        this.marka = marka;
        this.model = model;
        this.vrsta_goriva = vrsta_goriva;
        Vozilo.addHatchbackVozilo(this);
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
    public String toString() {
        return "Hatchback{" +
                "ID=" + ID +
                ", istekRegistracije=" + istekRegistracije +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", snaga=" + snaga +
                ", vrsta_goriva=" + vrsta_goriva +
                '}';
    }

    @Override
    public Integer getSnaga() {
        return this.snaga;
    }

}
