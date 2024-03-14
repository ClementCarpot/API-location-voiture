package com.accenture.dal.repository;

import com.accenture.dal.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LocationDao extends JpaRepository<Location, Integer>{
    List<Location> findByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin);
    List<Location> findByDateDebutNotAndDateFinNot(LocalDate dateDebut, LocalDate dateFin);
    @Query("SELECT l FROM Location l WHERE l.dateDebut >= :dateDebutParam AND l.dateFin <= :dateFinParam")
    List<Location> findLocationsBetweenDateDebutAndDateFin(LocalDate dateDebutParam, LocalDate dateFinParam);


}
