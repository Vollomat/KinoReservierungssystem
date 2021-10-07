package com.example.demo.controller;

import com.example.demo.EmailSenden;
import com.example.demo.entity.Bestellungen;
import com.example.demo.entity.Tickets;
import com.example.demo.repository.BestellungenRepository;
import com.example.demo.repository.TicketRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/tickets")
@RestController
public class TicketController {

    private TicketRepository ticketRepository;
    private BestellungenRepository bestellungenRepository;

    public TicketController(TicketRepository ticketRepository, BestellungenRepository bestellungenRepository) {
        this.ticketRepository = ticketRepository;
        this.bestellungenRepository = bestellungenRepository;
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
    public Tickets ticketAnlegen(@RequestBody Tickets ticket) {
        if(existiertTicketSchon(ticket)) {
            System.err.println("Das Ticket existiert schon!");
            return null;
        } else {
            System.out.println("Es wurde ein Ticket angelegt!");
            ticketRepository.save(ticket);

        }
        return ticket;
    }


    public boolean existiertTicketSchon(Tickets ticket) {

        ArrayList<Tickets> alleTickets = (ArrayList<Tickets>) ticketRepository.findAll();
        for (Tickets alleTicket : alleTickets) {
            if (alleTicket.getStartuhrzeit().equals(ticket.getStartuhrzeit()) && alleTicket.getFilmName().equals(ticket.getFilmName()) && alleTicket.getSitzplatzreihe().equals(ticket.getSitzplatzreihe()) && alleTicket.getSitzplatzspalte().equals(ticket.getSitzplatzspalte())) {
                return true;
            }
        }
        return false;
    }

}

