package com.accenture.service.dto;

import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.vehicules.TypeTransmission;
import com.accenture.dal.entity.vehicules.TypeEnergie;
import com.accenture.dal.entity.vehicules.TypeVoiture;

public record VoitureDto (
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
        int nombrePortes,
        TypeTransmission transmission,
        boolean climatisation,
        int nombreBagages,
        TypeVoiture typeVoiture,
        Permis permisNecessaire
) {
}
