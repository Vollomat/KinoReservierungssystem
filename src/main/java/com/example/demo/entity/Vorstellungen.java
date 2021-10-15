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


    private String filmName; //Fremdschlüssel

    private int kinosaalNummer; //Fremdschlüssel

    private String startuhrzeit;

    private String laengeDerVorstellungInMinuten;


    public Vorstellungen(int vorstellungsid, String filmName, int kinosaalNummer, String startuhrzeit, String laengeDerVorstellungInMinuten) {
        this.vorstellungsid = vorstellungsid;
        this.filmName = filmName;
        this.kinosaalNummer = kinosaalNummer;
        this.startuhrzeit = startuhrzeit;
        this.laengeDerVorstellungInMinuten = laengeDerVorstellungInMinuten;
    }

    public Vorstellungen() {

    }

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

    public int getKinosaalNummer() {
        return kinosaalNummer;
    }

    public void setKinosaalNummer(int kinosaalNummer) {
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
