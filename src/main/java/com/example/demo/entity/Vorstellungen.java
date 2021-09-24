package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vorstellungen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vorstellungsid;


    private String filmName;

    private String kinosaalNummer;

    private String startuhrzeit;

    private String laengeDerVorstellungInMinuten;

    public int getVorstellungsid() {
        return vorstellungsid;
    }

    public void setVorstellungsid(int vorstellungsid) {
        this.vorstellungsid = vorstellungsid;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getKinosaalNummer() {
        return kinosaalNummer;
    }

    public void setKinosaalNummer(String kinosaalNummer) {
        this.kinosaalNummer = kinosaalNummer;
    }

    public String getStartuhrzeit() {
        return startuhrzeit;
    }

    public void setStartuhrzeit(String startuhrzeit) {
        this.startuhrzeit = startuhrzeit;
    }

    public String getLaengeDerVorstellungInMinuten() {
        return laengeDerVorstellungInMinuten;
    }

    public void setLaengeDerVorstellungInMinuten(String laengeDerVorstellungInMinuten) {
        this.laengeDerVorstellungInMinuten = laengeDerVorstellungInMinuten;
    }


}
