package com.example.spring_futbol.repository;

import com.example.spring_futbol.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
