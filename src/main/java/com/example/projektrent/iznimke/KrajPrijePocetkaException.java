package com.example.projektrent.iznimke;

public class KrajPrijePocetkaException extends Exception{

    //ovo mora biti checked
    //

    public KrajPrijePocetkaException() {
    }

    public KrajPrijePocetkaException(String message) {
        super(message);
    }

    public KrajPrijePocetkaException(String message, Throwable cause) {
        super(message, cause);
    }

    public KrajPrijePocetkaException(Throwable cause) {
        super(cause);
    }
}
