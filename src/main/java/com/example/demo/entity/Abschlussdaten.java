package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Abschlussdaten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int abschlussdatenID;

    private int bestellID;

    private String bezahlart;


    public Abschlussdaten(int abschlussdatenID, int bestellID, String bezahlart) {
        this.abschlussdatenID = abschlussdatenID;
        this.bestellID = bestellID;
        this.bezahlart = bezahlart;
    }

    public Abschlussdaten() {

    }

    public int getAbschlussdatenID() {
        return abschlussdatenID;
    }

    public void setAbschlussdatenID(int abschlussdatenID) {
        this.abschlussdatenID = abschlussdatenID;
    }

    public int getBestellID() {
        return bestellID;
    }

    public void setBestellID(int bestellID) {
        this.bestellID = bestellID;
    }

    public String getBezahlart() {
        return bezahlart;
    }

    public void setBezahlart(String bezahlart) {
        this.bezahlart = bezahlart;
    }
}
