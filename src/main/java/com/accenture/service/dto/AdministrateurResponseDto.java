package com.accenture.service.dto;

import com.accenture.dal.entity.utilisateurs.Role;

public record AdministrateurResponseDto (
        String nom,
        String prenom,
        String email,
        String fonction,
        Role role
) {
}
