package com.example.projektrent.iznimke;

public class PredugaRezervacijaException extends RuntimeException{

    //ovo mora biti unchecked
    //app ce i dalje normalno raditi, samo ja ne zelim dopustiti da se rade predugacke rezervacije

    public PredugaRezervacijaException() {
    }

    public PredugaRezervacijaException(String message) {
        super(message);
    }

    public PredugaRezervacijaException(String message, Throwable cause) {
        super(message, cause);
    }

    public PredugaRezervacijaException(Throwable cause) {
        super(cause);
    }
}
