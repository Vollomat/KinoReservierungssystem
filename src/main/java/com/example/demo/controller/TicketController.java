package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/tickets")
@RestController
public class TicketController {

    private TicketRepository ticketRepository;
    private BestellungenRepository bestellungenRepository;
    private SitzplaetzeFuerVorstellungRepository sitzplaetzeFuerVorstellungRepository;
    private VorstellungRepository vorstellungRepository;
    private KundenRepository kundenRepository;

    public TicketController(TicketRepository ticketRepository, BestellungenRepository bestellungenRepository, SitzplaetzeFuerVorstellungRepository sitzplaetzeFuerVorstellungRepository, VorstellungRepository vorstellungRepository, KundenRepository kundenRepository) {
        this.ticketRepository = ticketRepository;
        this.bestellungenRepository = bestellungenRepository;
        this.sitzplaetzeFuerVorstellungRepository = sitzplaetzeFuerVorstellungRepository;
        this.vorstellungRepository = vorstellungRepository;
        this.kundenRepository = kundenRepository;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public List<Tickets> alleTickets() {
        System.out.println("GET wurde ausgeführt für alle Tickets!");
        return ticketRepository.findAll();
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @PostMapping(value = "/tickets", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int ticketAnlegen(@RequestBody Tickets ticket) {
        if(existiertTicketSchon(ticket)) {
            System.err.println("Das Ticket existiert schon!");
            return -1;
        } else {
            System.out.println("Es wurde ein Ticket angelegt!");
            if(sitzplatzStatusAufReserviertSetzen(ticket, "Reserviert")){
                double preisDesTicketsInklRabatt = ticketPreisBerechnung(ticket);
                Tickets dasTicket = new Tickets(ticket.getTicketid(), ticket.getStartuhrzeit(), kinosaalnummerBekommen(ticket), ticket.getFilmName(), preisDesTicketsInklRabatt, ticket.getAlterInJahren(), ticket.getSitzplatzreihe(), ticket.getSitzplatzspalte(), ticket.getBestellungID());
                ticketRepository.save(dasTicket);
                return (int) dasTicket.getPreis();
            }
        }
        return -1;
    }

    public int kinosaalnummerBekommen(Tickets ticket) {
        ArrayList<Vorstellungen> alleVorstellungen = (ArrayList<Vorstellungen>) vorstellungRepository.findAll();
        for (Vorstellungen vorstellungen : alleVorstellungen) {
            if (vorstellungen.getFilmName().equals(ticket.getFilmName()) && vorstellungen.getStartuhrzeit().equals(ticket.getStartuhrzeit())) {
                return vorstellungen.getKinosaalNummer();
            }
        }
        System.out.println("Fehler: Funktion kinosaalnummerBekommen kann nicht ausgeführt werden und findet nicht die passende Kinosaalnummer!");
        return -1;
    }


    public boolean existiertTicketSchon(Tickets ticket) {

        ArrayList<Tickets> alleTickets = (ArrayList<Tickets>) ticketRepository.findAll();
        for (Tickets alleTicket : alleTickets) {
            if (alleTicket.getStartuhrzeit().equals(ticket.getStartuhrzeit()) && alleTicket.getFilmName().equals(ticket.getFilmName()) && alleTicket.getSitzplatzreihe() == ticket.getSitzplatzreihe() && alleTicket.getSitzplatzspalte() == ticket.getSitzplatzspalte()) {
                return true;
            }
        }
        return false;
    }

    public boolean sitzplatzStatusAufReserviertSetzen(Tickets ticket, String neuerStatusSitzplatz) {
        int benoetigteVorstellungsID = 0;
        ArrayList<Vorstellungen> alleVorstellungen = (ArrayList<Vorstellungen>) vorstellungRepository.findAll();
        for (Vorstellungen vorstellungen : alleVorstellungen) {
            if (vorstellungen.getFilmName().equals(ticket.getFilmName()) && vorstellungen.getStartuhrzeit().equals(ticket.getStartuhrzeit())) {
                benoetigteVorstellungsID = vorstellungen.getVorstellungsid();
            }
        }
        ArrayList<SitzplaetzeFuerVorstellung> alleSitzplaetzeFuerVorstellung = (ArrayList<SitzplaetzeFuerVorstellung>) sitzplaetzeFuerVorstellungRepository.findAll();
        for (SitzplaetzeFuerVorstellung sitzplaetzeFuerVorstellung : alleSitzplaetzeFuerVorstellung) {
            if (sitzplaetzeFuerVorstellung.getVorstellungsID() == benoetigteVorstellungsID && sitzplaetzeFuerVorstellung.getReihe() == ticket.getSitzplatzreihe() && sitzplaetzeFuerVorstellung.getSpalte() == ticket.getSitzplatzspalte()) {
                if (sitzplaetzeFuerVorstellung.getStatusVomSitzplatz().equals("RESERVIERT")) {
                    System.err.println("Dieser Sitzplatz ist schon reserviert/gebucht!!!");
                    return false;
                } else {
                    sitzplaetzeFuerVorstellungRepository.delete(sitzplaetzeFuerVorstellung);
                    sitzplaetzeFuerVorstellungRepository.save(new SitzplaetzeFuerVorstellung(sitzplaetzeFuerVorstellung.getSitzplatzID(), sitzplaetzeFuerVorstellung.getReihe(), sitzplaetzeFuerVorstellung.getSpalte(), sitzplaetzeFuerVorstellung.getVorstellungsID(), neuerStatusSitzplatz));
                    System.out.println("Sitzplatzstatus wurde geändert für VorstellungsID: " + benoetigteVorstellungsID);
                    return true;
                }
            }
        }
        System.err.println("Unbekannter Fehler in Funktion: ticketStatusAufReserviertSetzen()!");
        return false;
    }

    public double ticketPreisBerechnung(Tickets ticket) {
        double anfangspreisDesTickets = 20.0; //Standardpreis

        double rabattstufenRabatt = 0;

        String emailDesKunden = "";
        //Zuerst Rabattstufe ermitteln anhand aller vergangenen Buchungen
        ArrayList<Bestellungen> alleBestellungen = (ArrayList<Bestellungen>) bestellungenRepository.findAll();
        ArrayList<Bestellungen> alleBestellungenDesKunden = new ArrayList<>();
        ArrayList<Tickets> alleTickets = (ArrayList<Tickets>) ticketRepository.findAll();
        ArrayList<Tickets> ticketsAllerBestellungenDesKunden = new ArrayList<>();

        for (Bestellungen value : alleBestellungen) {
            if (value.getBestellID() == ticket.getBestellungID()) {
                emailDesKunden = value.getEmail();
            }
        }
        for (Bestellungen bestellungen : alleBestellungen) {
            if (bestellungen.getEmail().equals(emailDesKunden)) {
                alleBestellungenDesKunden.add(bestellungen);
            }
        }
        for (Bestellungen bestellungen : alleBestellungenDesKunden) {
            for (Tickets alleTicket : alleTickets) {
                if (alleTicket.getBestellungID() == bestellungen.getBestellID()) {
                    ticketsAllerBestellungenDesKunden.add(alleTicket); //Wieviele Tickets hat der Kunde bestellt?
                }
            }
        }

        if(ticketsAllerBestellungenDesKunden.size() >= 5) {
            rabattstufenRabatt = 0.1;
            if(ticketsAllerBestellungenDesKunden.size() >= 10) {
                rabattstufenRabatt = 0.2;
                if(ticketsAllerBestellungenDesKunden.size() >= 20) {
                    rabattstufenRabatt = 0.3;
                }
            }
        }

        //Ist das Konto verifiziert?
        ArrayList<Kunden> alleKunden = (ArrayList<Kunden>) kundenRepository.findAll();
        if(!emailDesKunden.equals("")){
            for (Kunden kunden : alleKunden) {
                if (kunden.getEmail().equals(emailDesKunden)) {
                    if (kunden.isVerifiziert()) {
                        rabattstufenRabatt = rabattstufenRabatt + 0.05;
                    }
                }
            }
        }

        double neuerPreis = anfangspreisDesTickets*(1.0-rabattstufenRabatt);


        int vorstellungsID = -1;
        ArrayList<Vorstellungen> alleVorstellungen = (ArrayList<Vorstellungen>) vorstellungRepository.findAll();
        for (Vorstellungen vorstellungen : alleVorstellungen) { //VorstellungsID finden
            if (vorstellungen.getStartuhrzeit().equals(ticket.getStartuhrzeit()) && vorstellungen.getFilmName().equals(ticket.getFilmName())) {
                vorstellungsID = vorstellungen.getVorstellungsid();
            }
        }

        ArrayList<SitzplaetzeFuerVorstellung> alleSitzplaetze = (ArrayList<SitzplaetzeFuerVorstellung>) sitzplaetzeFuerVorstellungRepository.findAll();
        ArrayList<SitzplaetzeFuerVorstellung> alleRelevantenSitzplaetze = new ArrayList<>();
        for (SitzplaetzeFuerVorstellung sitzplaetzeFuerVorstellung : alleSitzplaetze) {
            if (sitzplaetzeFuerVorstellung.getVorstellungsID() == vorstellungsID) {
                alleRelevantenSitzplaetze.add(sitzplaetzeFuerVorstellung);
            }
        }

        ArrayList<SitzplaetzeFuerVorstellung> alleGebuchtenSitzplaetzeDieserVorstellung = new ArrayList<>();

        for (SitzplaetzeFuerVorstellung sitzplaetzeFuerVorstellung : alleRelevantenSitzplaetze) {
            if (sitzplaetzeFuerVorstellung.getStatusVomSitzplatz().equals("GEBUCHT")) {
                alleGebuchtenSitzplaetzeDieserVorstellung.add(sitzplaetzeFuerVorstellung);
            }
        }

        //Berechnung
        double rabattAuslastung = 0.0;
        double auslastungInProzent = (double) 100 * alleGebuchtenSitzplaetzeDieserVorstellung.size() / alleRelevantenSitzplaetze.size();
        if(auslastungInProzent < 50.0) {
            rabattAuslastung = 0.02;
            if(auslastungInProzent < 25.0) {
                rabattAuslastung = 0.05;
                if(auslastungInProzent < 10.0) {
                    rabattAuslastung = 0.12;
                }
            }
        }

        neuerPreis = neuerPreis*(1-rabattAuslastung);

        //Alter des Kunden
        double rabattAlter = 0.0;
        if(ticket.getAlterInJahren() < 27) {
            rabattAlter = 0.1;
            if(ticket.getAlterInJahren() < 18) {
                rabattAlter = 0.25;
                if(ticket.getAlterInJahren() < 10) {
                    rabattAlter = 0.5;
                }
            }
        }

        neuerPreis = neuerPreis*(1-rabattAlter);

        return (int) neuerPreis;
    }


    public TicketRepository getTicketRepository() {
        return ticketRepository;
    }

    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public BestellungenRepository getBestellungenRepository() {
        return bestellungenRepository;
    }

    public void setBestellungenRepository(BestellungenRepository bestellungenRepository) {
        this.bestellungenRepository = bestellungenRepository;
    }

    public SitzplaetzeFuerVorstellungRepository getSitzplaetzeFuerVorstellungRepository() {
        return sitzplaetzeFuerVorstellungRepository;
    }

    public void setSitzplaetzeFuerVorstellungRepository(SitzplaetzeFuerVorstellungRepository sitzplaetzeFuerVorstellungRepository) {
        this.sitzplaetzeFuerVorstellungRepository = sitzplaetzeFuerVorstellungRepository;
    }

    public VorstellungRepository getVorstellungRepository() {
        return vorstellungRepository;
    }

    public void setVorstellungRepository(VorstellungRepository vorstellungRepository) {
        this.vorstellungRepository = vorstellungRepository;
    }

    public KundenRepository getKundenRepository() {
        return kundenRepository;
    }

    public void setKundenRepository(KundenRepository kundenRepository) {
        this.kundenRepository = kundenRepository;
    }
}

