package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kunden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


   private String vorname;

   private String nachname;

   private String email;

   private int alterInJahren;

   private String strasse;

   private String hausnummer;

   private String postleitzahl;

   private String ort;

   private String passwort;

   private boolean verifiziert;

    public Kunden(int id, String vorname, String nachname, String email, int alterInJahren, String strasse, String hausnummer, String postleitzahl, String ort, String passwort, boolean verifiziert) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.alterInJahren = alterInJahren;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.postleitzahl = postleitzahl;
        this.ort = ort;
        this.passwort = passwort;
        this.verifiziert = verifiziert;
    }

    public Kunden(){

    }

    public boolean isVerifiziert() {
        return verifiziert;
    }

    public void setVerifiziert(boolean verifiziert) {
        this.verifiziert = verifiziert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAlterInJahren() {
        return alterInJahren;
    }

    public void setAlterInJahren(int alterInJahren) {
        this.alterInJahren = alterInJahren;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
