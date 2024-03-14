package com.accenture.service.mapper;

import com.accenture.dal.entity.vehicules.Moto;
import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.vehicules.TypeTransmission;
import com.accenture.dal.entity.vehicules.TypeMoto;
import com.accenture.service.dto.MotoDto;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-01T11:02:15+0100",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class MotoMapperImpl implements MotoMapper {
    @Override
    public Moto motoDtoToMoto(MotoDto dto) {
        if (dto == null) {
            return null;
        }

        Moto moto = new Moto();

        moto.setId(dto.id());
        moto.setMarque(dto.marque());
        moto.setModele(dto.modele());
        moto.setCouleur(dto.couleur());
        moto.setTarifJournalier(dto.tarifJournalier());
        moto.setKilometrage(dto.kilometrage());
        moto.setActif(dto.actif());
        moto.setRetireDuParc(dto.retireDuParc());
        moto.setNombreDeCylindres(dto.nombreDeCylindres());
        moto.setCylindree(dto.cylindree());
        moto.setPoids(dto.poids());
        moto.setPuissanceKW(dto.puissanceKW());
        moto.setHauteurSelle(dto.hauteurSelle());
        moto.setTransmission(dto.transmission());
        moto.setTypeMoto(dto.typeMoto());
        moto.setPermisNecessaire(dto.permisNecessaire());

        return moto;
    }

    @Override
    public MotoDto motoToMotoDto(Moto entity) {
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
        int nombreDeCylindres = 0;
        int cylindree = 0;
        int poids = 0;
        int puissanceKW = 0;
        int hauteurSelle = 0;
        TypeTransmission transmission = null;
        TypeMoto typeMoto = null;
        Permis permisNecessaire = null;

        id = entity.getId();
        marque = entity.getMarque();
        modele = entity.getModele();
        couleur = entity.getCouleur();
        tarifJournalier = entity.getTarifJournalier();
        kilometrage = entity.getKilometrage();
        actif = entity.isActif();
        retireDuParc = entity.isRetireDuParc();
        nombreDeCylindres = entity.getNombreDeCylindres();
        cylindree = entity.getCylindree();
        poids = entity.getPoids();
        puissanceKW = entity.getPuissanceKW();
        hauteurSelle = entity.getHauteurSelle();
        transmission = entity.getTransmission();
        typeMoto = entity.getTypeMoto();
        permisNecessaire = entity.getPermisNecessaire();

        MotoDto motoDto = new MotoDto(id, marque, modele, couleur, tarifJournalier, kilometrage, actif, retireDuParc, nombreDeCylindres, cylindree, poids, puissanceKW, hauteurSelle, transmission, typeMoto, permisNecessaire);

        return motoDto;
    }
}
