package com.example.demo.controller;

import com.example.demo.entity.Bestellungen;
import com.example.demo.entity.Tickets;
import com.example.demo.repository.BestellungenRepository;
import com.example.demo.repository.KundenRepository;
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
        for (int i = 0; i < alleBestellungen.size(); i++) {
            if(alleBestellungen.get(i).getVorstellungsID().equals(bestellungen.getVorstellungsID())) {
                if (alleBestellungen.get(i).getSitzplatzreihe().equals(bestellungen.getSitzplatzreihe())) {
                    if (alleBestellungen.get(i).getSitzplatzspalte().equals(bestellungen)) {
                        System.err.println("Der Sitzplatz ist bereits gebucht!");
                        return false;
                    }
                }
            }
        }
        bestellungenRepository.save(bestellungen);
        return true;
    }

}
