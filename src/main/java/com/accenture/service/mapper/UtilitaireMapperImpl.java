package com.accenture.service.mapper;

import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.vehicules.TypeEnergie;
import com.accenture.dal.entity.vehicules.TypeUtilitaire;
import com.accenture.dal.entity.vehicules.Utilitaire;
import com.accenture.service.dto.UtilitaireDto;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-01T11:02:15+0100",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class UtilitaireMapperImpl implements UtilitaireMapper {
    @Override
    public Utilitaire utilitaireDtoToUtilitaire(UtilitaireDto dto) {
        if (dto == null) {
            return null;
        }

        Utilitaire utilitaire = new Utilitaire();

        utilitaire.setId(dto.id());
        utilitaire.setMarque(dto.marque());
        utilitaire.setModele(dto.modele());
        utilitaire.setCouleur(dto.couleur());
        utilitaire.setNombrePlaces(dto.nombrePlaces());
        utilitaire.setTarifJournalier(dto.tarifJournalier());
        utilitaire.setKilometrage(dto.kilometrage());
        utilitaire.setActif(dto.actif());
        utilitaire.setRetireDuParc(dto.retireDuParc());
        utilitaire.setTypeEnergie(dto.typeEnergie());
        utilitaire.setTransmission(dto.transmission());
        utilitaire.setClimatisation(dto.climatisation());
        utilitaire.setChargeMax(dto.chargeMax());
        utilitaire.setPoidsPATC(dto.poidsPATC());
        utilitaire.setCapaciteMetreCube(dto.capaciteMetreCube());
        utilitaire.setTypeUtilitaire(dto.typeUtilitaire());
        utilitaire.setPermisNecessaire(dto.permisNecessaire());

        return utilitaire;
    }

    @Override
    public UtilitaireDto utilitaireToUtilitaireDto(Utilitaire entity) {
        if (entity == null) {
            return null;
        }

        int id = 0;
        String marque = null;
        String modele = null;
        String couleur = null;
        int nombrePlaces = 0;
        double tarifJournalier = 0.0d;
        int kilometrage = 0;
        boolean actif = false;
        boolean retireDuParc = false;
        TypeEnergie typeEnergie = null;
        int chargeMax = 0;
        int poidsPATC = 0;
        int capaciteMetreCube = 0;
        TypeUtilitaire typeUtilitaire = null;
        Permis permisNecessaire = null;

        id = entity.getId();
        marque = entity.getMarque();
        modele = entity.getModele();
        couleur = entity.getCouleur();
        nombrePlaces = entity.getNombrePlaces();
        tarifJournalier = entity.getTarifJournalier();
        kilometrage = entity.getKilometrage();
        actif = entity.isActif();
        retireDuParc = entity.isRetireDuParc();
        typeEnergie = entity.getTypeEnergie();
        chargeMax = entity.getChargeMax();
        poidsPATC = entity.getPoidsPATC();
        capaciteMetreCube = entity.getCapaciteMetreCube();
        typeUtilitaire = entity.getTypeUtilitaire();
        permisNecessaire = entity.getPermisNecessaire();

        UtilitaireDto utilitaireDto =  new UtilitaireDto(id, marque, modele, couleur, nombrePlaces, tarifJournalier, kilometrage, actif, retireDuParc, typeEnergie, entity.getTransmission(), entity.isClimatisation(), chargeMax, poidsPATC, capaciteMetreCube, typeUtilitaire, permisNecessaire);

        return utilitaireDto;
    }
}
