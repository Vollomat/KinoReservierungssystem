package com.example.demo.controller;

import com.example.demo.KinoticketReservierungssystem;
import com.example.demo.entity.OMDBFilme;
import com.example.demo.repository.OMDBRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/filmAnlegen")
@RestController
public class OMDBController {

    private OMDBRepository omdbRepository;

    public OMDBController(OMDBRepository omdbRepository) {
        this.omdbRepository = omdbRepository;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public void filmeEinfuegen() throws IOException {
        String[] omdbTerminator = new String[5];
        omdbTerminator[0] = "http://www.omdbapi.com/?s=terminator&type=movie&apikey=96cfc15e";
        omdbTerminator[1] = "http://www.omdbapi.com/?s=transformers&type=movie&apikey=96cfc15e";
        omdbTerminator[2] = "http://www.omdbapi.com/?s=black%20widow&type=movie&apikey=96cfc15e";
        omdbTerminator[3] = "http://www.omdbapi.com/?s=wonder%20woman%201984&type=movie&apikey=96cfc15e";
        omdbTerminator[4] = "http://www.omdbapi.com/?s=soul&type=movie&apikey=96cfc15e";
        for(int i = 0; i < omdbTerminator.length; i++) {
            OMDBFilme omdbFilm = KinoticketReservierungssystem.datenbankEintrag(KinoticketReservierungssystem.datenbankAbfrageOMDBAPI(omdbTerminator[i]));
            omdbRepository.save(omdbFilm);
        }
    }


}
