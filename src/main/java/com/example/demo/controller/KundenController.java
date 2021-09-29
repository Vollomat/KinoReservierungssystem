package com.example.demo.controller;

import com.example.demo.EmailSenden;
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

    private KundenRepository kundenRepository;

    public KundenController(KundenRepository kundenRepository) {
        this.kundenRepository = kundenRepository;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public List<Kunden> alleKunden() {
        System.out.println("GET wurde ausgeführt für alle Kunden");
        return kundenRepository.findAll();
    }

    @RequestMapping(value ="register", produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value ="register", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean kundenAnlegen(@RequestBody Kunden kunden) {
        ArrayList<Kunden> alleKunden = (ArrayList<Kunden>) kundenRepository.findAll();
        for(int i = 0; i < alleKunden.size(); i++) {
            if(alleKunden.get(i).getEmail().equals(kunden.getEmail())){
                System.err.println("Der mitgegebene Kunde mit der E-Mail " + kunden.getEmail() + " existiert schon!");
                return false;
            }
        }
        System.out.println("Ein Kunde wurde angelegt!");
        kundenRepository.save(kunden);
        return true;
    }

    @RequestMapping(value = "/passwortvergessen",produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value ="/passwortvergessen", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void passwortVergessen(@RequestBody String email) {
        System.out.println("Der Kunde mit der E-Mail " + email + " hat sein Passwort vergessen!");
        ArrayList<Kunden> alleKunden = new ArrayList<>();
        for(int i = 0; i < kundenRepository.findAll().size(); i++) {
            alleKunden.add(kundenRepository.findAll().get(i));
        }
        for(int j = 0; j < alleKunden.size(); j++) {
            if(alleKunden.get(j).getEmail().equals(email)){
                String message = "Ihr Passwort ist: " + alleKunden.get(j).getPasswort();
                EmailSenden.emailversand(email, message);
            }
        }
    }

    @RequestMapping(value = "/login",produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value ="/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Kunden einloggen(@RequestBody Einloggdaten einloggdaten) {
        ArrayList<Kunden> alleKunden = (ArrayList<Kunden>) alleKunden();
        for(int i=0; i<alleKunden.size(); i++) {
            System.out.println("Passwort:" + alleKunden.get(i).getPasswort().equals(einloggdaten.getPasswort()));
            if(alleKunden.get(i).getPasswort().equals(einloggdaten.getPasswort()) && alleKunden.get(i).getEmail().equals(einloggdaten.getEmail())) {
                return alleKunden.get(i);
            }
        }
        return null;
    }

}
