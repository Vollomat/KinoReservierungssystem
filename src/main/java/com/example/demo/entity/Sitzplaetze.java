package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sitzplaetze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sitzplatzID;

    private int reihe;

    private int spalte;

    private int kinosaalID; //Fremdschlüssel


    public Sitzplaetze(int sitzplatzID, int reihe, int spalte, int kinosaalID) {
        this.sitzplatzID = sitzplatzID;
        this.reihe = reihe;
        this.spalte = spalte;
        this.kinosaalID = kinosaalID;
    }

    public Sitzplaetze() {

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

    public int getKinosaalID() {
        return kinosaalID;
    }

    public void setKinosaalID(int kinosaalID) {
        this.kinosaalID = kinosaalID;
    }
}
