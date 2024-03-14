package com.accenture.service.mapper;

import com.accenture.dal.entity.*;
import com.accenture.dal.entity.vehicules.*;
import com.accenture.service.dto.VoitureDto;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.List;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-01T11:02:15+0100",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class VoitureMapperImpl implements VoitureMapper {
    @Override
    public Voiture voitureDtoToVoiture(VoitureDto dto) {
        if (dto == null) {
            return null;
        }

        Voiture voiture = new Voiture();

        voiture.setId(dto.id());
        voiture.setMarque(dto.marque());
        voiture.setModele(dto.modele());
        voiture.setCouleur(dto.couleur());
        voiture.setNombrePlaces(dto.nombrePlaces());
        voiture.setTarifJournalier(dto.tarifJournalier());
        voiture.setKilometrage(dto.kilometrage());
        voiture.setActif(dto.actif());
        voiture.setRetireDuParc(dto.retireDuParc());
        voiture.setTypeEnergie(dto.typeEnergie());
        voiture.setNombrePortes(dto.nombrePortes());
        voiture.setTransmission(dto.transmission());
        voiture.setClimatisation(dto.climatisation());
        voiture.setNombreBagages(dto.nombreBagages());
        voiture.setTypeVoiture(dto.typeVoiture());
        voiture.setPermisNecessaire(dto.permisNecessaire());

        return voiture;
    }

    @Override
    public VoitureDto voitureToVoitureDto(Voiture entity) {
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
        int nombrePortes = 0;
        TypeTransmission transmission = null;
        boolean climatisation = false;
        int nombreBagages = 0;
        TypeVoiture typeVoiture = null;
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
        nombrePortes = entity.getNombrePortes();
        transmission = entity.getTransmission();
        climatisation = entity.isClimatisation();
        nombreBagages = entity.getNombreBagages();
        typeVoiture = entity.getTypeVoiture();
        permisNecessaire = entity.getPermisNecessaire();

        VoitureDto voitureDto = new VoitureDto(id, marque, modele, couleur, nombrePlaces, tarifJournalier, kilometrage, actif, retireDuParc, typeEnergie, nombrePortes, transmission, climatisation, nombreBagages, typeVoiture, permisNecessaire);

        return voitureDto;
    }
}
