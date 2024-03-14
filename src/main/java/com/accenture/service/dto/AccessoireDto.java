package com.accenture.service.dto;

import com.accenture.dal.entity.AccessoirePourTypeVehicule;

public record AccessoireDto (
        int id,
        String nom,
        double montantUnitaire,
        int limite,
        AccessoirePourTypeVehicule accessoirePourTypeVehicule
) {
}
