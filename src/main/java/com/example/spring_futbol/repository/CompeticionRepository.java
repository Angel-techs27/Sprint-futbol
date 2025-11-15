package com.example.spring_futbol.repository;

import com.example.spring_futbol.entity.Competicion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompeticionRepository extends JpaRepository<Competicion, Long> {
}