package com.example.demo.controller;

import com.example.demo.entity.Kunden;
import com.example.demo.repository.KundenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kunden")
public class KundenController {

    private KundenRepository kundenRepository;

    public KundenController(KundenRepository kundenRepository) {
        this.kundenRepository = kundenRepository;
    }

    @GetMapping("")
    public List<Kunden> index() {
        System.out.println("Beginn GET");
        kundenLoeschen();
        return kundenRepository.findAll();
    }

    public boolean kundenLoeschen(){
        List<Kunden> alleKunden = kundenRepository.findAll();
        boolean gefunden = false;
        for(Kunden test : alleKunden) {
            if(test.getNachname().equals("Mustermann")) {
                kundenRepository.delete(test);
                gefunden = true;
            }
        }
        System.out.println("Wurde gelöscht --> " + gefunden);
        return gefunden;
    }

    //Das ist ein Test

}
