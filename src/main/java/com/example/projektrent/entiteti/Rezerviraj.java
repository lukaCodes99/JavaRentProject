package com.example.projektrent.entiteti;

import com.example.projektrent.BazaPodataka;
import com.example.projektrent.iznimke.IstekRegistracijeException;
import com.example.projektrent.iznimke.KrajPrijePocetkaException;
import com.example.projektrent.iznimke.PredugaRezervacijaException;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public interface Rezerviraj<T> {

    default void rezervirajVozilo(Integer id, LocalDate pocetak, LocalDate kraj) throws KrajPrijePocetkaException,
            PredugaRezervacijaException, IstekRegistracijeException {
        //vidjeti kasnije treba li mi ovo vozila, vjerojatno treba

        long daysBetween = ChronoUnit.DAYS.between(pocetak, kraj);
        if(daysBetween>30){
            throw new PredugaRezervacijaException("Maksimum rezervacije je 30 dana");
        }
        if(pocetak.isAfter(kraj)){
            throw new KrajPrijePocetkaException("Kraj ne smije biti prije pocetka");
        }
        /*
        if(kraj.isAfter(hatch.getIstekRegistracije())){
            throw new  IstekRegistracijeException("Registracija ističe za vrijeme najma");
        }

         */
        else {
            BazaPodataka.dodajRezervaciju(new Rezervacija(pocetak, kraj, id));
            //ReservationsTable.addReservation(hatch.getRezervacija());
        }


        }
    }




