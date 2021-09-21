package com.example.demo.controller;

import com.example.demo.entity.Kunden;
import com.example.demo.repository.KundenRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/kunden")
@RestController
public class KundenController {

    private KundenRepository kundenRepository;

    public KundenController(KundenRepository kundenRepository) {
        this.kundenRepository = kundenRepository;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public List<Kunden> alleKunden() {
        System.out.println("GET wurde ausgeführt für alle Kunden");
        return kundenRepository.findAll();
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value ="/kunden", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void kundenAnlegen(@RequestBody Kunden kunden) {
        kundenRepository.save(kunden);
    }


}
