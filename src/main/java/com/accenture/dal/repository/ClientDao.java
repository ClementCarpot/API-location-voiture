package com.accenture.dal.repository;

import com.accenture.dal.entity.utilisateurs.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ClientDao extends JpaRepository<Client, Integer> {
    List<Client> findByNom(String nom);
    List<Client> findByPrenom(String prenom);
    boolean existsByEmailIgnoreCase(String email);
    Client findByEmailIgnoreCaseAndMotDePasse(String email, String motDePasse);
    List<Client> findAllByDesactiveFalse();
}
