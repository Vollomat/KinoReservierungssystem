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

    private String email;  //Eindeutige ID, egal ob Gast oder Kundenkonto. Versand der Tickets dorthin. Eine Bestellung hat mehrere Tickets.

    private String bezahlart;

    private int pruefnummer;

    public Bestellungen(int bestellID, String email, String bezahlart, int pruefnummer) {
        this.bestellID = bestellID;
        this.email = email;
        this.bezahlart = bezahlart;
        this.pruefnummer = pruefnummer;
    }

    public Bestellungen() {

    }

    public int getPruefnummer() {
        return pruefnummer;
    }

    public void setPruefnummer(int pruefnummer) {
        this.pruefnummer = pruefnummer;
    }

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

}
