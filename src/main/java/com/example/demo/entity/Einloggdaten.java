package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Einloggdaten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int einloggdatenID;

    private String email;

    private String passwort;


    public int getEinloggdatenID() {
        return einloggdatenID;
    }

    public void setEinloggdatenID(int einloggdatenID) {
        this.einloggdatenID = einloggdatenID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
