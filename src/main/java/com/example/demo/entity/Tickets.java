package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketid;

    private String startuhrzeit;

    private String kinosaalNummer;

    private String filmName;

    private String preis;

    private int sitzplatzreihe;

    private int sitzplatzspalte;

    private int bestellungID;


    public int getBestellungID() {
        return bestellungID;
    }

    public void setBestellungID(int bestellungID) {
        this.bestellungID = bestellungID;
    }

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public String getStartuhrzeit() {
        return startuhrzeit;
    }

    public void setStartuhrzeit(String startuhrzeit) {
        this.startuhrzeit = startuhrzeit;
    }

    public String getKinosaalNummer() {
        return kinosaalNummer;
    }

    public void setKinosaalNummer(String kinosaalNummer) {
        this.kinosaalNummer = kinosaalNummer;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public int getSitzplatzreihe() {
        return sitzplatzreihe;
    }

    public void setSitzplatzreihe(int sitzplatzreihe) {
        this.sitzplatzreihe = sitzplatzreihe;
    }

    public int getSitzplatzspalte() {
        return sitzplatzspalte;
    }

    public void setSitzplatzspalte(int sitzplatzspalte) {
        this.sitzplatzspalte = sitzplatzspalte;
    }
}