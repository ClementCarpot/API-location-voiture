package com.accenture.dal.repository;

import com.accenture.dal.entity.vehicules.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureDao extends JpaRepository<Voiture, Integer> {
}
