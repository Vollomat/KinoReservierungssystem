package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OMDBFilme {


    @Id
    private String titel;


    private String jahr;

    private String imdbID;

    private String urlBild;

    public OMDBFilme(String titel, String jahr, String imdbID, String urlBild) {
        this.titel = titel;
        this.jahr = jahr;
        this.imdbID = imdbID;
        this.urlBild = urlBild;
    }

    public OMDBFilme() {

    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getJahr() {
        return jahr;
    }

    public void setJahr(String jahr) {
        this.jahr = jahr;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getUrlBild() {
        return urlBild;
    }

    public void setUrlBild(String urlBild) {
        this.urlBild = urlBild;
    }

}
