package com.example.spring_futbol.repository;

import com.example.spring_futbol.entity.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

}