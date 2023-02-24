package com.example.projektrent.entiteti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Vozilo<T> {

    private Integer ID;
    private static Integer brojVozila=0;
    private String tipVozila;
    String marka;
    String model;
    private static Map<Integer, Vozilo> vozila=new HashMap<>();
    public Vozilo(Integer ID, String tipVozila, String marka, String model) {
        brojVozila++;
        this.marka=marka;
        this.model=model;
        this.ID = ID;
        this.tipVozila=tipVozila;
    }


    public Integer getID() {
        return ID;
    }

    public String getTipVozila() {
        return tipVozila;
    }

    public void setTipVozila(String tipVozila) {
        this.tipVozila = tipVozila;
    }

    public static Integer getBrojVozila() {
        return brojVozila;
    }

    public  Map<Integer, Vozilo> getVozila() {
        return vozila;
    }

    public static void addHatchbackVozilo(Vozilo<Hatchback> hatchback) {
        vozila.put(hatchback.getID(), hatchback);
    }
    public static void addKombiVozilo(Kombi kombi) {
        vozila.put(kombi.getID(), kombi);
    }
    public static void addLimuzinaVozilo(Vozilo<Limuzina> limo){
        vozila.put(limo.getID(), limo);
    }
    public abstract Integer getSnaga();

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }
}

