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
        return vorstellungRepository.findAll();
    }

    @RequestMapping(value ="/filmbekommen", produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value ="/filmbekommen", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Vorstellungen[] alleZVorstellungen(@RequestBody String filmname) {
        Vorstellungen[] vorstellungenzurueck = new Vorstellungen[4];
        int gefundeneElemente = 0;
        ArrayList<Vorstellungen> alleVorstellungen = (ArrayList<Vorstellungen>) vorstellungRepository.findAll();
        System.out.println(filmname);
        for (Vorstellungen vorstellungen : alleVorstellungen) {
            if (vorstellungen.getFilmName().equals(filmname)) {
                if (gefundeneElemente < 4) {
                    vorstellungenzurueck[gefundeneElemente] = vorstellungen;
                    gefundeneElemente++;
                }
            }
        }
        System.out.println("function aufgerufen");
        return vorstellungenzurueck;
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
        for (Vorstellungen value : alleVorstellungen) {
            if (vorstellungen.getFilmName().equals(value.getFilmName()) && vorstellungen.getStartuhrzeit().equals(value.getStartuhrzeit())) {
                gewuenschteVorstellung = value;
            }
        }
        for (SitzplaetzeFuerVorstellung sitzplaetzeFuerVorstellung : alleSitzplaetzeFuerVorstellung) {
            if (gewuenschteVorstellung != null) {
                if (sitzplaetzeFuerVorstellung.getVorstellungsID() == gewuenschteVorstellung.getVorstellungsid()) {
                    gewuenschterSitzplan.add(sitzplaetzeFuerVorstellung);
                }
            }
        }
        return gewuenschterSitzplan;
    }




    public void sitzplanFuerVorstellungAnlegen(Vorstellungen vorstellungen) {
        int gewuenschterKinoSaal = vorstellungen.getKinosaalNummer();
        ArrayList<Vorstellungen> alleVorstellungen = (ArrayList<Vorstellungen>) vorstellungRepository.findAll();
        int gewuenschteVorstellungsID = 0;
        for (Vorstellungen value : alleVorstellungen) {
            if (value.getKinosaalNummer() == vorstellungen.getKinosaalNummer() && value.getLaengeDerVorstellungInMinuten().equals(vorstellungen.getLaengeDerVorstellungInMinuten()) && value.getStartuhrzeit().equals(vorstellungen.getStartuhrzeit())) {
                gewuenschteVorstellungsID = value.getVorstellungsid();
            }
        }

        if (sitzplaetzeRepository.findAll().size() > 0) {
            ArrayList<Sitzplaetze> alleSitzplaetze = (ArrayList<Sitzplaetze>) sitzplaetzeRepository.findAll();
            for (Sitzplaetze sitzplaetze : alleSitzplaetze) {
                if (sitzplaetze.getKinosaalID() == gewuenschterKinoSaal) {
                    SitzplaetzeFuerVorstellung sitzplaetzeFuerVorstellung = new SitzplaetzeFuerVorstellung(sitzplaetze.getSitzplatzID(), sitzplaetze.getReihe(), sitzplaetze.getSpalte(), gewuenschteVorstellungsID, "FREI");
                    sitzplaetzeFuerVorstellungRepository.save(sitzplaetzeFuerVorstellung);
                }
            }
        }
    }

    public boolean existiertVorstellungSchon(Vorstellungen vorstellung) {
        ArrayList<Vorstellungen> alleVorstellungen = (ArrayList<Vorstellungen>) vorstellungRepository.findAll();
        for (Vorstellungen vorstellungen : alleVorstellungen) {
            if (vorstellungen.getFilmName().equals(vorstellung.getFilmName()) && vorstellungen.getStartuhrzeit().equals(vorstellung.getStartuhrzeit()) && vorstellungen.getKinosaalNummer() == vorstellung.getKinosaalNummer()) {
                return true;
            }
        }
        return false;
    }


}