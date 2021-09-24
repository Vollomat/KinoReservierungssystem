package com.example.demo.controller;

import com.example.demo.entity.Vorstellungen;
import com.example.demo.javaklassen.Vorstellung;
import com.example.demo.repository.VorstellungRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/vorstellungen")
@RestController
public class VorstellungController {

    private VorstellungRepository vorstellungRepository;

    public VorstellungController(VorstellungRepository vorstellungRepository) {
        this.vorstellungRepository = vorstellungRepository;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public List<Vorstellungen> alleVorstellungen() {
        System.out.println("GET wurde ausgeführt für alle Vorstellungen");
        return vorstellungRepository.findAll();
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value ="/vorstellungen", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void kundenAnlegen(@RequestBody Vorstellungen vorstellung) {
        if(existiertVorstellungSchon(vorstellung)) {
            System.err.println("Die Vorstellung existiert schon!");
        } else {
            System.out.println("Es wurde eine Vorstellung angelegt!");
            vorstellungRepository.save(vorstellung);
        }
    }

    public boolean existiertVorstellungSchon(Vorstellungen vorstellung) {
        boolean ergebnis = false;

        ArrayList<Vorstellungen> alleVorstellungen = (ArrayList<Vorstellungen>) vorstellungRepository.findAll();
        for(int i = 0; i < alleVorstellungen.size(); i++) {
            if(alleVorstellungen.get(i).getFilmName().equals(vorstellung.getFilmName()) && alleVorstellungen.get(i).getStartuhrzeit().equals(vorstellung.getStartuhrzeit())) {
                ergebnis = true;
            }
        }

        return ergebnis;
    }


}