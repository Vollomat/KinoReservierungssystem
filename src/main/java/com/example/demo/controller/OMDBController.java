package com.example.demo.controller;

import com.example.demo.KinoticketReservierungssystem;
import com.example.demo.entity.OMDBFilme;
import com.example.demo.repository.OMDBRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/filmAnlegen")
@RestController
public class OMDBController {

    private final OMDBRepository omdbRepository;

    public OMDBController(OMDBRepository omdbRepository) {
        this.omdbRepository = omdbRepository;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public boolean filmeEinfuegen() throws IOException {
        String[] omdbAPIAbfragen = new String[12];
        omdbAPIAbfragen[0] = "http://www.omdbapi.com/?s=terminator&type=movie&apikey=96cfc15e";
        omdbAPIAbfragen[1] = "http://www.omdbapi.com/?s=transformers&type=movie&apikey=96cfc15e";
        omdbAPIAbfragen[2] = "http://www.omdbapi.com/?s=black%20widow&type=movie&apikey=96cfc15e";
        omdbAPIAbfragen[3] = "http://www.omdbapi.com/?s=wonder%20woman%201984&type=movie&apikey=96cfc15e";
        omdbAPIAbfragen[4] = "http://www.omdbapi.com/?s=soul&type=movie&apikey=96cfc15e";
        omdbAPIAbfragen[5] = "http://www.omdbapi.com/?s=free%20guy&type=movie&apikey=96cfc15e";
        omdbAPIAbfragen[6] = "https://www.omdbapi.com/?s=after&type=movie&apikey=96cfc15e";
        omdbAPIAbfragen[7] = "https://www.omdbapi.com/?s=the%20hobbit&type=movie&apikey=96cfc15e";
        omdbAPIAbfragen[8] = "https://www.omdbapi.com/?s=creed&type=movie&apikey=96cfc15e";
        omdbAPIAbfragen[9] = "https://www.omdbapi.com/?s=avengers&type=movie&apikey=96cfc15e";
        omdbAPIAbfragen[10] = "https://www.omdbapi.com/?s=avatar&type=movie&apikey=96cfc15e";
        omdbAPIAbfragen[11] = "https://www.omdbapi.com/?s=joker&type=movie&apikey=96cfc15e";

        for (int i = 0; i < omdbAPIAbfragen.length; i++) {
            OMDBFilme omdbFilm = KinoticketReservierungssystem.datenbankEintrag(KinoticketReservierungssystem.datenbankAbfrageOMDBAPI(omdbAPIAbfragen[i]), i);
            omdbRepository.save(omdbFilm);
        }
        OMDBFilme omdbFilm = KinoticketReservierungssystem.datenbankEintrag(KinoticketReservierungssystem.datenbankAbfrageOMDBAPI(omdbAPIAbfragen[0]), 0);
        omdbRepository.save(omdbFilm);
        return omdbRepository.findAll().size() > 10;
    }


}
