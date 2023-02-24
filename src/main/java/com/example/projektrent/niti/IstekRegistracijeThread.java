package com.example.projektrent.niti;
import com.example.projektrent.BazaPodataka;
import com.example.projektrent.GlavniEkranController;
import com.example.projektrent.HelloApplication;
import com.example.projektrent.entiteti.Hatchback;
import com.example.projektrent.entiteti.Kombi;
import com.example.projektrent.entiteti.Limuzina;
import com.example.projektrent.entiteti.Vozilo;
import javafx.application.Application;
import javafx.application.Platform;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class IstekRegistracijeThread implements Runnable {
    private final String type;
    private final int count;

    public IstekRegistracijeThread(String type, int count) {
        this.type = type;
        this.count = count;
    }

    //probao sam sa wildcardovima ali nisam siguran kako napraviti "pretvorbu", vjerojatno dodati metodu
    //i bolje napraviti nasljedivanje
    private int countIstekRegistracijeH(List<Hatchback> listaHatchback) {
        int countIstek = 0;
        LocalDate now = LocalDate.now();
        for (Hatchback h : listaHatchback) {
            long daysUntilIstek = ChronoUnit.DAYS.between(now, h.getIstekRegistracije());
            if (daysUntilIstek >= 0 && daysUntilIstek <= 7) {
                countIstek++;
            }
        }
        return countIstek;
    }

    private int countIstekRegistracijeL(List<Limuzina> listaLimuzina) {
        int countIstek = 0;
        LocalDate now = LocalDate.now();
        for (Limuzina l : listaLimuzina) {
            long daysUntilIstek = ChronoUnit.DAYS.between(now, l.getIstekRegistracije());
            if (daysUntilIstek >= 0 && daysUntilIstek <= 7) {
                countIstek++;
            }
        }
        return countIstek;
    }

    private int countIstekRegistracijeK(List<Kombi> listaKombi) {
        int countIstek = 0;
        LocalDate now = LocalDate.now();
        for (Kombi k : listaKombi) {
            long daysUntilIstek = ChronoUnit.DAYS.between(now, k.getIstekRegistracije());
            if (daysUntilIstek >= 0 && daysUntilIstek <= 7) {
                countIstek++;
            }
        }
        return countIstek;
    }

    @Override
    public void run() {
        while (true){
            switch (type) {
                case "Hatchback":
                    List<Hatchback> listaHatchback = BazaPodataka.dohvatiSvaHatchbackVozila();
                    int countHatchback = countIstekRegistracijeH(listaHatchback);
                    Platform.runLater(() -> {
                        HelloApplication.getMainStage().setTitle((String.format("Broj hatchback vozila sa istekom" +
                                " registracije u idućih 7 dana: %d", countHatchback)));
                    });
                    break;
                case "Limuzina":
                    List<Limuzina> listaLimuzina = BazaPodataka.dohvatiSvaLimuzinaVozila();
                    int countLimuzina = countIstekRegistracijeL(listaLimuzina);
                    Platform.runLater(() -> {
                        HelloApplication.getMainStage().setTitle((String.format("Broj limuzina sa istekom" +
                                " registracije u idućih 7 dana: %d", countLimuzina)));
                    });
                    break;
                case "Kombi":
                    List<Kombi> listaKombi = BazaPodataka.dohvatiSvaKombiVozila();
                    int countKombi = countIstekRegistracijeK(listaKombi);
                    Platform.runLater(() -> {
                        HelloApplication.getMainStage().setTitle((String.format("Broj kombi vozila sa istekom " +
                                "registracije u idućih 7 dana: %d", countKombi)));
                    });
                    break;
            }
            //bez run later-neka greska
            //trebalo bi se malo bolje izmjenjivati ali ne znam kako, vjerojatno treba pratiti koliko se puta nesto prikazalo
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

