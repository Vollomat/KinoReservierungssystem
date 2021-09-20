package com.example.demo.repository;

import com.example.demo.entity.Kunden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundenRepository extends JpaRepository<Kunden, Integer> {

}
