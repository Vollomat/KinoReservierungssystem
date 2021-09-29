package com.example.demo.repository;

import com.example.demo.entity.Bestellungen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestellungenRepository extends JpaRepository<Bestellungen, Integer> {
}
