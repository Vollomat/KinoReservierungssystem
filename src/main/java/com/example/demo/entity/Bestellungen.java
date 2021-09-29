package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bestellungen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bestellID;

    private String email;  //Eindeutige ID, egal ob Gast oder Kundenkonto. Versand der Tickets dorthin

    private String bezahlart;

    private String filmName;

    private String vorstellungsID;

    private String sitzplatzreihe;

    private String sitzplatzspalte;


    public int getBestellID() {
        return bestellID;
    }

    public void setBestellID(int bestellID) {
        this.bestellID = bestellID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBezahlart() {
        return bezahlart;
    }

    public void setBezahlart(String bezahlart) {
        this.bezahlart = bezahlart;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getVorstellungsID() {
        return vorstellungsID;
    }

    public void setVorstellungsID(String vorstellungsID) {
        this.vorstellungsID = vorstellungsID;
    }

    public String getSitzplatzreihe() {
        return sitzplatzreihe;
    }

    public void setSitzplatzreihe(String sitzplatzreihe) {
        this.sitzplatzreihe = sitzplatzreihe;
    }

    public String getSitzplatzspalte() {
        return sitzplatzspalte;
    }

    public void setSitzplatzspalte(String sitzplatzspalte) {
        this.sitzplatzspalte = sitzplatzspalte;
    }
}
