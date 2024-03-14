package com.accenture.service.dto;

import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.vehicules.TypeTransmission;
import com.accenture.dal.entity.vehicules.TypeEnergie;
import com.accenture.dal.entity.vehicules.TypeUtilitaire;

public record UtilitaireDto (
        int id,
        String marque,
        String modele,
        String couleur,
        int nombrePlaces,
        double tarifJournalier,
        int kilometrage,
        boolean actif,
        boolean retireDuParc,
        TypeEnergie typeEnergie,
        TypeTransmission transmission,
        boolean climatisation,
        int chargeMax,
        int poidsPATC,
        int capaciteMetreCube,
        TypeUtilitaire typeUtilitaire,
        Permis permisNecessaire
) {
}
