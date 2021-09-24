package com.example.demo.controller;

import com.example.demo.entity.Vorstellungen;
import com.example.demo.repository.VorstellungRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("Es wurde eine Vorstellung angelegt!");
        vorstellungRepository.save(vorstellung);
    }

}