package com.accenture.service.dto;

import com.accenture.dal.entity.Accessoire;
import com.accenture.dal.entity.EtatLocation;
import com.accenture.dal.entity.utilisateurs.Client;
import com.accenture.dal.entity.vehicules.Vehicule;

import java.time.LocalDate;
import java.util.List;

public record LocationDto (
        int id,
        Client client,
        Vehicule vehicule,
        List<Accessoire> accessoires,
        LocalDate dateDebut,
        LocalDate dateFin,
        int nombreKmParcourus,
        double montantLocation,
        LocalDate dateValidation,
        EtatLocation etatLocation
) {
}
