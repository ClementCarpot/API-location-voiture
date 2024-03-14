package com.accenture.dal.repository;

import com.accenture.dal.entity.vehicules.Utilitaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilitaireDao extends JpaRepository<Utilitaire, Integer> {
}
