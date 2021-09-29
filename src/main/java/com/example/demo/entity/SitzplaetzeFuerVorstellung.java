package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SitzplaetzeFuerVorstellung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sitzplatzID;

    private int reihe;

    private int spalte;

    private int vorstellungsID; //Fremdschlüssel

    private String statusVomSitzplatz;

    public SitzplaetzeFuerVorstellung(int sitzplatzID, int reihe, int spalte, int vorstellungsID, String statusVomSitzplatz) {
        this.sitzplatzID = sitzplatzID;
        this.reihe = reihe;
        this.spalte = spalte;
        this.vorstellungsID = vorstellungsID;
        this.statusVomSitzplatz = statusVomSitzplatz;
    }

    public int getSitzplatzID() {
        return sitzplatzID;
    }

    public void setSitzplatzID(int sitzplatzID) {
        this.sitzplatzID = sitzplatzID;
    }

    public int getReihe() {
        return reihe;
    }

    public void setReihe(int reihe) {
        this.reihe = reihe;
    }

    public int getSpalte() {
        return spalte;
    }

    public void setSpalte(int spalte) {
        this.spalte = spalte;
    }

    public int getVorstellungsID() {
        return vorstellungsID;
    }

    public void setVorstellungsID(int vorstellungsID) {
        this.vorstellungsID = vorstellungsID;
    }

    public String getStatusVomSitzplatz() {
        return statusVomSitzplatz;
    }

    public void setStatusVomSitzplatz(String statusVomSitzplatz) {
        this.statusVomSitzplatz = statusVomSitzplatz;
    }
}
