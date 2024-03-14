package com.accenture.service.mapper;

import com.accenture.dal.entity.Accessoire;
import com.accenture.dal.entity.AccessoirePourTypeVehicule;
import com.accenture.service.dto.AccessoireDto;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-01T11:02:15+0100",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class AccessoireMapperImpl implements AccessoireMapper {
    @Override
    public Accessoire accessoireDtoToAccessoire(AccessoireDto dto) {
        if (dto == null) {
            return null;
        }

        Accessoire accessoire = new Accessoire();

        accessoire.setId(dto.id());
        accessoire.setNom(dto.nom());
        accessoire.setMontantUnitaire(dto.montantUnitaire());
        accessoire.setLimite(dto.limite());

        return accessoire;
    }

    @Override
    public AccessoireDto accessoireToAccessoireDto(Accessoire entity) {
        if (entity == null) {
            return null;
        }

        int id = 0;
        String nom = null;
        double montantUnitaire = 0.0d;
        int limite = 0;
        AccessoirePourTypeVehicule accessoirePourTypeVehicule = null;

        id = entity.getId();
        nom = entity.getNom();
        montantUnitaire = entity.getMontantUnitaire();
        limite = entity.getLimite();
        accessoirePourTypeVehicule = entity.getAccessoirePourTypeVehicule();

        AccessoireDto accessoireDto = new AccessoireDto(id, nom, montantUnitaire, limite, accessoirePourTypeVehicule);

        return accessoireDto;
    }
}
