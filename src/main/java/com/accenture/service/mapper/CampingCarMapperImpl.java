package com.accenture.service.mapper;

import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.vehicules.CampingCar;
import com.accenture.dal.entity.vehicules.TypeCampingCar;
import com.accenture.dal.entity.vehicules.TypeEnergie;
import com.accenture.dal.entity.vehicules.TypeTransmission;
import com.accenture.service.dto.CampingCarDto;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-01T11:02:15+0100",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class CampingCarMapperImpl implements CampingCarMapper {
    @Override
    public CampingCar campingCarDtoToCampingCar(CampingCarDto dto) {
        if (dto == null) {
            return null;
        }

        CampingCar campingCar = new CampingCar();

        campingCar.setId(dto.id());
        campingCar.setMarque(dto.marque());
        campingCar.setModele(dto.modele());
        campingCar.setCouleur(dto.couleur());
        campingCar.setNombrePlaces(dto.nombrePlaces());
        campingCar.setTarifJournalier(dto.tarifJournalier());
        campingCar.setKilometrage(dto.kilometrage());
        campingCar.setActif(dto.actif());
        campingCar.setRetireDuParc(dto.retireDuParc());
        campingCar.setTypeEnergie(dto.typeEnergie());
        campingCar.setTransmission(dto.transmission());
        campingCar.setClimatisation(dto.climatisation());
        campingCar.setPoidsPATC(dto.poidsPATC());
        campingCar.setHauteur(dto.hauteur());
        campingCar.setNombreCouchages(dto.nombreCouchages());
        campingCar.setEquipementCuisine(dto.equipementCuisine());
        campingCar.setLingeLit(dto.lingeLit());
        campingCar.setEquipementRefregirateur(dto.equipementRefregirateur());
        campingCar.setEquipementDouche(dto.equipementDouche());
        campingCar.setTypeCampingCar(dto.typeCampingCar());
        campingCar.setPermisNecessaire(dto.permisNecessaire());

        return campingCar;
    }

    @Override
    public CampingCarDto campingCarToCampingCarDto(CampingCar entity) {
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
        TypeTransmission transmission = null;
        boolean climatisation = false;
        int poidsPATC = 0;
        int hauteur = 0;
        int nombreCouchages = 0;
        boolean equipementCuisine = false;
        boolean lingeLit = false;
        boolean equipementRefregirateur = false;
        boolean equipementDouche = false;
        TypeCampingCar typeCampingCar = null;
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
        transmission = entity.getTransmission();
        climatisation = entity.isClimatisation();
        poidsPATC = entity.getPoidsPATC();
        hauteur = entity.getHauteur();
        nombreCouchages = entity.getNombreCouchages();
        equipementCuisine = entity.isEquipementCuisine();
        lingeLit = entity.isLingeLit();
        equipementRefregirateur = entity.isEquipementRefregirateur();
        equipementDouche = entity.isEquipementDouche();
        typeCampingCar = entity.getTypeCampingCar();
        permisNecessaire = entity.getPermisNecessaire();

        CampingCarDto campingCarDto = new CampingCarDto(id, marque, modele, couleur, nombrePlaces, tarifJournalier, kilometrage, actif, retireDuParc, typeEnergie, transmission, climatisation, poidsPATC, hauteur, nombreCouchages, equipementCuisine, lingeLit, equipementRefregirateur, equipementDouche, typeCampingCar, permisNecessaire);

        return campingCarDto;
    }
}
