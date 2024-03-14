package com.accenture.service.dto;

import com.accenture.dal.entity.utilisateurs.Role;

public record AdministrateurDto(
        int id,
        String nom,
        String prenom,
        String fonction,
        String email,
        String motDePasse,
        Role role
) {
}
