package com.example.demo.repository;

import com.example.demo.entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Tickets, Integer> {

}
