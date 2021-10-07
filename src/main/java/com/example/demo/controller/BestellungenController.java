package com.example.demo.controller;

import com.example.demo.entity.Bestellungen;
import com.example.demo.repository.BestellungenRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/bestellungen")
@RestController
public class BestellungenController {

    private BestellungenRepository bestellungenRepository;

    public BestellungenController(BestellungenRepository bestellungenRepository) {
        this.bestellungenRepository = bestellungenRepository;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean bestellungAnlegen(@RequestBody Bestellungen bestellungen) {
        ArrayList<Bestellungen> alleBestellungen = (ArrayList<Bestellungen>) bestellungenRepository.findAll();
        for (Bestellungen value : alleBestellungen) {
            if (value.getVorstellungsID().equals(bestellungen.getVorstellungsID())) {
                if (value.getSitzplatzreihe().equals(bestellungen.getSitzplatzreihe())) {
                    System.err.println("Der Sitzplatz ist bereits gebucht!");
                    return false;
                }
            }
        }
        bestellungenRepository.save(bestellungen);
        return true;
    }

}
