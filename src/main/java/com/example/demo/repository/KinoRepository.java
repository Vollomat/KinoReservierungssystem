package com.example.demo.repository;

import com.example.demo.entity.Kinos;
import com.example.demo.entity.OMDBFilme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KinoRepository extends JpaRepository<Kinos, Integer> {



}
