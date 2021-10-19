package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Einloggdaten {

    @Id
    @GeneratedValue()
    private String email;

    private String passwort;

    public Einloggdaten() {

    }

    public Einloggdaten(String email, String passwort) {
        this.email = email;
        this.passwort = passwort;
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
