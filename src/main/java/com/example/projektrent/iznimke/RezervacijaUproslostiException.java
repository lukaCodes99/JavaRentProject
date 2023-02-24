package com.example.projektrent.iznimke;

public class RezervacijaUproslostiException extends Exception{

    //ovo je checked, s obzirom da cemo imati thread koji ce provjeravati je li kraj rese u proslosti
    //ako thread vidi da je kraj rese u proslosti odmah ce staviti na dostupan(true)
    //mozda i nije toliko bitno da se oznaci kako checked, ali neka bude tu

    public RezervacijaUproslostiException() {
    }

    public RezervacijaUproslostiException(String message) {
        super(message);
    }

    public RezervacijaUproslostiException(String message, Throwable cause) {
        super(message, cause);
    }

    public RezervacijaUproslostiException(Throwable cause) {
        super(cause);
    }
}
