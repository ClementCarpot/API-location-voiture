package com.accenture.service.dto;

import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.vehicules.TypeTransmission;
import com.accenture.dal.entity.vehicules.TypeMoto;

public record MotoDto (
        int id,
        String marque,
        String modele,
        String couleur,
        double tarifJournalier,
        int kilometrage,
        boolean actif,
        boolean retireDuParc,
        int nombreDeCylindres,
        int cylindree,
        int poids,
        int puissanceKW,
        int hauteurSelle,
        TypeTransmission transmission,
        TypeMoto typeMoto,
        Permis permisNecessaire
) {
}
