package com.example.demo.controller;

import com.example.demo.entity.Sitzplaetze;
import com.example.demo.entity.SitzplaetzeFuerVorstellung;
import com.example.demo.entity.Vorstellungen;
import com.example.demo.repository.SitzplaetzeFuerVorstellungRepository;
import com.example.demo.repository.SitzplaetzeRepository;
import com.example.demo.repository.VorstellungRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/vorstellungen")
@RestController
public class VorstellungController {

    private VorstellungRepository vorstellungRepository;
    private SitzplaetzeRepository sitzplaetzeRepository;
    private SitzplaetzeFuerVorstellungRepository sitzplaetzeFuerVorstellungRepository;

    public VorstellungController(VorstellungRepository vorstellungRepository, SitzplaetzeRepository sitzplaetzeRepository, SitzplaetzeFuerVorstellungRepository sitzplaetzeFuerVorstellungRepository) {
        this.vorstellungRepository = vorstellungRepository;
        this.sitzplaetzeRepository = sitzplaetzeRepository;
        this.sitzplaetzeFuerVorstellungRepository = sitzplaetzeFuerVorstellungRepository;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public List<Vorstellungen> alleVorstellungen() {
        System.out.println("GET wurde ausgeführt für alle Vorstellungen");
        return vorstellungRepository.findAll();
    }


    @RequestMapping(value ="/anlegen", produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value ="/anlegen", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void vorstellungAnlegen(@RequestBody Vorstellungen vorstellung) {
        if(existiertVorstellungSchon(vorstellung)) {
            System.err.println("Die Vorstellung existiert schon!");
        } else {
            System.out.println("Es wurde eine Vorstellung angelegt!");
            vorstellungRepository.save(vorstellung);
            sitzplanFuerVorstellungAnlegen(vorstellung);
        }
    }

    @RequestMapping(value ="/sitzplaetze", produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value ="/sitzplaetze", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<SitzplaetzeFuerVorstellung> sitzplaetzeDerVorstellung(@RequestBody Vorstellungen vorstellungen) {
        Vorstellungen gewuenschteVorstellung = null;
        ArrayList<SitzplaetzeFuerVorstellung> gewuenschterSitzplan = new ArrayList<>();
        ArrayList<Vorstellungen> alleVorstellungen = (ArrayList<Vorstellungen>) vorstellungRepository.findAll();
        ArrayList<SitzplaetzeFuerVorstellung> alleSitzplaetzeFuerVorstellung = (ArrayList<SitzplaetzeFuerVorstellung>) sitzplaetzeFuerVorstellungRepository.findAll();
        for(int i = 0; i < alleVorstellungen.size(); i++) {
            if(vorstellungen.getFilmName().equals(alleVorstellungen.get(i).getFilmName()) && vorstellungen.getStartuhrzeit().equals(alleVorstellungen.get(i).getStartuhrzeit())){
                gewuenschteVorstellung = alleVorstellungen.get(i);
            }
        }
        for(int i = 0; i < alleSitzplaetzeFuerVorstellung.size(); i++) {
            if(gewuenschteVorstellung != null) {
                if(alleSitzplaetzeFuerVorstellung.get(i).getVorstellungsID() == gewuenschteVorstellung.getVorstellungsid()) {
                    gewuenschterSitzplan.add(alleSitzplaetzeFuerVorstellung.get(i));
                }
            }
        }
        return gewuenschterSitzplan;
    }




    public void sitzplanFuerVorstellungAnlegen(Vorstellungen vorstellungen) {
        int gewuenschterKinoSaal = vorstellungen.getKinosaalNummer();
        ArrayList<Vorstellungen> alleVorstellungen = (ArrayList<Vorstellungen>) vorstellungRepository.findAll();
        int gewuenschteVorstellungsID = 0;
        for(int i = 0; i < alleVorstellungen.size(); i++) {
            if(alleVorstellungen.get(i).getKinosaalNummer() == vorstellungen.getKinosaalNummer() && alleVorstellungen.get(i).getLaengeDerVorstellungInMinuten().equals(vorstellungen.getLaengeDerVorstellungInMinuten()) && alleVorstellungen.get(i).getStartuhrzeit().equals(vorstellungen.getStartuhrzeit())){
                gewuenschteVorstellungsID = alleVorstellungen.get(i).getVorstellungsid();
            }
        }

        if (sitzplaetzeRepository.findAll() != null) {
            ArrayList<Sitzplaetze> alleSitzplaetze = (ArrayList<Sitzplaetze>) sitzplaetzeRepository.findAll();
            for (int i = 0; i < alleSitzplaetze.size(); i++) {
                if (alleSitzplaetze.get(i).getKinosaalID() == gewuenschterKinoSaal) {
                    SitzplaetzeFuerVorstellung sitzplaetzeFuerVorstellung = new SitzplaetzeFuerVorstellung(alleSitzplaetze.get(i).getSitzplatzID(), alleSitzplaetze.get(i).getReihe(), alleSitzplaetze.get(i).getSpalte(), gewuenschteVorstellungsID, "FREI");
                    sitzplaetzeFuerVorstellungRepository.save(sitzplaetzeFuerVorstellung);
                }
            }
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