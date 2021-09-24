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


    private String vornameDesBesitzers;

    private String nachnameDesBesitzers;

    private String startuhrzeit;

    private String kinosaalNummer;

    private String filmName;

    private String preis;

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public String getVornameDesBesitzers() {
        return vornameDesBesitzers;
    }

    public void setVornameDesBesitzers(String vornameDesBesitzers) {
        this.vornameDesBesitzers = vornameDesBesitzers;
    }

    public String getNachnameDesBesitzers() {
        return nachnameDesBesitzers;
    }

    public void setNachnameDesBesitzers(String nachnameDesBesitzers) {
        this.nachnameDesBesitzers = nachnameDesBesitzers;
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
}