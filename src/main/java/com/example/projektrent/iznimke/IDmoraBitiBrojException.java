package com.example.projektrent.iznimke;

public class IDmoraBitiBrojException extends Exception{

    //ovo mora biti checked!
    /*
    It depends on how you catch the exception. If you catch an unchecked exception and handle it,
     will not change the fact that it's an unchecked exception. The reason it is called an unchecked exception is
     that the compiler does not check if it is being handled in the code or not. The point of unchecked exceptions
     is that they represent conditions that are unlikely to be handled or recoverable in the code, so it's
     not necessary for the compiler to enforce their handling. That being said, you can still catch an
     unchecked exception and handle it in your code if you choose to do so. But if you do handle it,
     it won't change the fact that it's an unchecked exception.
     */

    //znaci checked u principu znaci da app nece dalje dobro raditi ako nismo to rijesili

    public IDmoraBitiBrojException() {
    }

    public IDmoraBitiBrojException(String message) {
        super(message);
    }

    public IDmoraBitiBrojException(String message, Throwable cause) {
        super(message, cause);
    }

    public IDmoraBitiBrojException(Throwable cause) {
        super(cause);
    }
}
