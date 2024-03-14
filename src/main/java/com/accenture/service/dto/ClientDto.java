package com.accenture.service.dto;

import com.accenture.dal.entity.utilisateurs.Adresse;
import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.utilisateurs.Role;

import java.time.LocalDate;
import java.util.List;

public record ClientDto(
        int id,
        String nom,
        String prenom,
        Adresse adresse,
        String email,
        String motDePasse,
        LocalDate dateNaissance,
        LocalDate dateInscription,
        List<Permis> permis,
        boolean desactive,
        Role role

) {
}
