package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kinos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kinoID;

    private String strasse;

    private String hausnummer;

    private String plz;

    private String ort;


    public int getKinoID() {
        return kinoID;
    }

    public void setKinoID(int kinoID) {
        this.kinoID = kinoID;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
