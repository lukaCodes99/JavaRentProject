package com.example.projektrent.entiteti;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    public final Rola rola;
    private final String username;
    private final String hashedPassword;

    public User(Rola rola, String username, String password) {
        this.rola = rola;
        this.username = username;
        this.hashedPassword = hashPassword(password);
    }

    public boolean validateLozinka(String password) {

        return hashPassword(password).equals(hashedPassword);
    }

    public String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Rola getRola() {
        return rola;
    }

    public String getUsername() {
        return username;
    }
}
