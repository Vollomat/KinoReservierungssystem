package com.example.demo.controller;

import com.example.demo.entity.Einloggdaten;
import com.example.demo.entity.Kunden;
import com.example.demo.repository.KundenRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/kunden")
@RestController
public class KundenController {

    private final KundenRepository kundenRepository;

    public KundenController(KundenRepository kundenRepository) {
        this.kundenRepository = kundenRepository;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public List<Kunden> alleKunden() {
        return kundenRepository.findAll();
    }

    @RequestMapping(value = "/register", produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean kundenAnlegen(@RequestBody Kunden kunden) {
        ArrayList<Kunden> alleKunden = (ArrayList<Kunden>) kundenRepository.findAll();
        for (Kunden value : alleKunden) {
            if (value.getEmail().equals(kunden.getEmail())) {
                System.err.println("Der mitgegebene Kunde mit der E-Mail " + kunden.getEmail() + " existiert schon!");
                return false;
            }
        }
        System.out.println("Ein Kunde wurde angelegt!");
        kundenRepository.save(kunden);
        return true;
    }

    @RequestMapping(value = "/passwortvergessen", produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value = "/passwortvergessen", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String passwortVergessen(@RequestBody String email) {
        System.out.println("Der Kunde mit der E-Mail " + email + " hat sein Passwort vergessen!");
        ArrayList<Kunden> alleKunden = new ArrayList<>(kundenRepository.findAll());
        for (Kunden kunden : alleKunden) {
            if (kunden.getEmail().equals(email)) {
                String message = "Ihr Passwort ist: " + kunden.getPasswort();
                //EmailSenden.emailversand(email, message); //Todo
                return message;
            }
        }
        System.err.println("Die E-Mail " + email + " existiert nicht!");
        return null;
    }

    @RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Kunden einloggen(@RequestBody Einloggdaten einloggdaten) {
        ArrayList<Kunden> alleKunden = (ArrayList<Kunden>) alleKunden();
        for (Kunden kunden : alleKunden) {
            if (kunden.getEmail().equals(einloggdaten.getEmail()) && kunden.getPasswort().equals(einloggdaten.getPasswort())) {
                return kunden;
            }
        }
        return null;
    }

    public KundenRepository getKundenRepository() {
        return kundenRepository;
    }

}
