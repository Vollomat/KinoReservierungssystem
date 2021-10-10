package com.example.demo.controller;

import com.example.demo.entity.SitzplaetzeFuerVorstellung;
import com.example.demo.entity.Tickets;
import com.example.demo.entity.Vorstellungen;
import com.example.demo.repository.BestellungenRepository;
import com.example.demo.repository.SitzplaetzeFuerVorstellungRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.VorstellungRepository;
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

    public TicketController(TicketRepository ticketRepository, BestellungenRepository bestellungenRepository, SitzplaetzeFuerVorstellungRepository sitzplaetzeFuerVorstellungRepository, VorstellungRepository vorstellungRepository) {
        this.ticketRepository = ticketRepository;
        this.bestellungenRepository = bestellungenRepository;
        this.sitzplaetzeFuerVorstellungRepository = sitzplaetzeFuerVorstellungRepository;
        this.vorstellungRepository = vorstellungRepository;
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
    public boolean ticketAnlegen(@RequestBody Tickets ticket) {
        if(existiertTicketSchon(ticket)) {
            System.err.println("Das Ticket existiert schon!");
            return false;
        } else {
            System.out.println("Es wurde ein Ticket angelegt!");
            if(sitzplatzStatusAufReserviertSetzen(ticket, "Reserviert")){
                ticketRepository.save(ticket);
            }
        }
        return true;
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

}

