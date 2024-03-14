package com.accenture.service.dto;

import com.accenture.dal.entity.vehicules.Batterie;
import com.accenture.dal.entity.vehicules.TypeVelo;

public record VeloDto (
        int id,
        String marque,
        String modele,
        String couleur,
        double tarifJournalier,
        int kilometrage,
        boolean actif,
        boolean retireDuParc,
        String tailleCadre,
        int poidsKg,
        boolean isElectrique,
        Batterie batterie,
        boolean freinsDisque,
        TypeVelo typeVelo
) {
}
