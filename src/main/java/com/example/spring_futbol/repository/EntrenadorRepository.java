package com.example.spring_futbol.repository;

import com.example.spring_futbol.entity.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {

}