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

    private int kinosaalNummer;

    private String filmName;

    private double preis;

    private int alterInJahren;

    private int sitzplatzreihe;

    private int sitzplatzspalte;

    private int bestellungID;

    public Tickets(int ticketid, String startuhrzeit, int kinosaalNummer, String filmName, double preis, int alterInJahren, int sitzplatzreihe, int sitzplatzspalte, int bestellungID) {
        this.ticketid = ticketid;
        this.startuhrzeit = startuhrzeit;
        this.kinosaalNummer = kinosaalNummer;
        this.filmName = filmName;
        this.preis = preis;
        this.alterInJahren = alterInJahren;
        this.sitzplatzreihe = sitzplatzreihe;
        this.sitzplatzspalte = sitzplatzspalte;
        this.bestellungID = bestellungID;
    }

    public Tickets() {

    }

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

    public int getKinosaalNummer() {
        return kinosaalNummer;
    }

    public void setKinosaalNummer(int kinosaalNummer) {
        this.kinosaalNummer = kinosaalNummer;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
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

    public int getAlterInJahren() {
        return alterInJahren;
    }

    public void setAlterInJahren(int alterInJahren) {
        this.alterInJahren = alterInJahren;
    }
}