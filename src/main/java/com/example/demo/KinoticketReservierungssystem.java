package com.example.demo;

import com.example.demo.entity.OMDBFilme;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootApplication
public class KinoticketReservierungssystem {

    public static void main(String[] args) {
        SpringApplication.run(KinoticketReservierungssystem.class, args);

    }


    public static String datenbankAbfrageOMDBAPI(String urlFuerOMDB) throws IOException {

        URL url = new URL(urlFuerOMDB);
        HttpURLConnection verbindung = (HttpURLConnection) url.openConnection();
        verbindung.setRequestMethod("GET");
        BufferedReader bufferedreader = new BufferedReader(
                new InputStreamReader(verbindung.getInputStream()));
        String eingehenderText;
        StringBuffer ergebnis = new StringBuffer();
        while ((eingehenderText = bufferedreader.readLine()) != null) {
            ergebnis.append(eingehenderText);
        }
        bufferedreader.close();
        verbindung.disconnect();

        return ergebnis.toString();
    }

    public static OMDBFilme datenbankEintrag(String omdbErgebnis) throws JsonProcessingException {
        boolean geklappt = false;
        String[] strings = omdbErgebnis.split("\"");
        String titel = strings[5];
        String jahr = strings[5+4];
        String imdbID = strings[5+4+4];
        String urlfuerBild = strings[5+4+4+8];
        System.out.println("Folgender Film wird in die Datenbank testdatenbank eingefügt:");
        System.out.println("titel: " + titel + ", jahr: " + jahr + ", imdbID: " + imdbID + ", urlBild:" + urlfuerBild);
        System.out.println();
        OMDBFilme omdbFilm = new OMDBFilme(titel, jahr, imdbID, urlfuerBild);
        return omdbFilm;
    }


}
