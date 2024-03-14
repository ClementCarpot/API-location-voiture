package com.accenture.service.mapper;

import com.accenture.dal.entity.vehicules.Batterie;
import com.accenture.dal.entity.vehicules.TypeVelo;
import com.accenture.dal.entity.vehicules.Velo;
import com.accenture.service.dto.VeloDto;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-01T11:02:15+0100",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class VeloMapperImpl implements VeloMapper {
    @Override
    public Velo veloDtoToVelo(VeloDto dto) {
        if (dto == null) {
            return null;
        }

        Velo velo = new Velo();

        velo.setId(dto.id());
        velo.setMarque(dto.marque());
        velo.setModele(dto.modele());
        velo.setCouleur(dto.couleur());
        velo.setTarifJournalier(dto.tarifJournalier());
        velo.setKilometrage(dto.kilometrage());
        velo.setActif(dto.actif());
        velo.setRetireDuParc(dto.retireDuParc());
        velo.setTailleCadre(dto.tailleCadre());
        velo.setPoidsKg(dto.poidsKg());
        velo.setElectrique(dto.isElectrique());
        velo.setFreinsDisque(dto.freinsDisque());
        velo.setTypeVelo(dto.typeVelo());
        velo.setBatterie(dto.batterie());

        return velo;
    }

    @Override
    public VeloDto veloToVeloDto(Velo entity) {
        if (entity == null) {
            return null;
        }

        int id = 0;
        String marque = null;
        String modele = null;
        String couleur = null;
        double tarifJournalier = 0.0d;
        int kilometrage = 0;
        boolean actif = false;
        boolean retireDuParc = false;
        String tailleCadre = null;
        int poidsKg = 0;
        boolean isElectrique = false;
        boolean freinsDisque = false;
        TypeVelo typeVelo = null;
        Batterie batterie = null;

        id = entity.getId();
        marque = entity.getMarque();
        modele = entity.getModele();
        couleur = entity.getCouleur();
        tarifJournalier = entity.getTarifJournalier();
        kilometrage = entity.getKilometrage();
        actif = entity.isActif();
        retireDuParc = entity.isRetireDuParc();
        tailleCadre = entity.getTailleCadre();
        poidsKg = entity.getPoidsKg();
        isElectrique = entity.isElectrique();
        freinsDisque = entity.isFreinsDisque();
        typeVelo = entity.getTypeVelo();
        batterie = entity.getBatterie();

        VeloDto veloDto = new VeloDto(id, marque, modele, couleur, tarifJournalier, kilometrage, actif, retireDuParc, tailleCadre, poidsKg, isElectrique, batterie, freinsDisque, typeVelo);

        return veloDto;
    }
}
