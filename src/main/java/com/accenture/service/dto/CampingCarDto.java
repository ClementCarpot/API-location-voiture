package com.accenture.service.dto;

import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.vehicules.TypeCampingCar;
import com.accenture.dal.entity.vehicules.TypeTransmission;
import com.accenture.dal.entity.vehicules.TypeEnergie;

public record CampingCarDto(
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
        int poidsPATC,
        int hauteur,
        int nombreCouchages,
        boolean equipementCuisine,
        boolean lingeLit,
        boolean equipementRefregirateur,
        boolean equipementDouche,
        TypeCampingCar typeCampingCar,
        Permis permisNecessaire
) {
}
