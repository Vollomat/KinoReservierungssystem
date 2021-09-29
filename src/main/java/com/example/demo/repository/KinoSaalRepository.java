package com.example.demo.repository;

import com.example.demo.entity.KinoSaale;
import com.example.demo.entity.OMDBFilme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KinoSaalRepository extends JpaRepository<KinoSaale, Integer> {


}
