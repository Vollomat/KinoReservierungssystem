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


    private String email;

    private String startuhrzeit;

    private String kinosaalNummer;

    private String filmName;

    private String preis;

    private String status;

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}