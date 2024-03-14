package com.accenture.dal.repository;

import com.accenture.dal.entity.utilisateurs.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurDao extends JpaRepository<Administrateur, Integer> {
    boolean existsByEmailIgnoreCase(String email);

    Administrateur findByEmailIgnoreCaseAndMotDePasse(String email, String motDePasse);
}
