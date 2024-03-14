package com.accenture.service;

import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.vehicules.CampingCar;
import com.accenture.dal.repository.CampingCarDao;
import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.CampingCarDto;
import com.accenture.service.mapper.CampingCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampingCarServiceImpl implements CampingCarService {

    private final CampingCarMapper campingCarMapper;

    private final CampingCarDao campingCarDao;

    @Autowired
    public CampingCarServiceImpl(CampingCarMapper campingCarMapper, CampingCarDao campingCarDao) {
        this.campingCarMapper = campingCarMapper;
        this.campingCarDao = campingCarDao;
    }

    @Override
    public void ajouter(CampingCarDto campingCarDto) throws VehiculeException {

        if (campingCarDto == null)
            throw new VehiculeException("Le camping-car est nul");

        CampingCar campingCar = campingCarMapper.campingCarDtoToCampingCar(campingCarDto);

        if (campingCarDto.marque() == null || campingCarDto.marque().isBlank())
            throw new VehiculeException("La marque est obligatoire");
        if (campingCarDto.modele() == null || campingCarDto.modele().isBlank())
            throw new VehiculeException("Le modèle est obligatoire");
        if (campingCarDto.couleur() == null || campingCarDto.couleur().isBlank())
            throw new VehiculeException("La couleur est obligatoire");
        if (campingCarDto.nombrePlaces() <= 0)
            throw new VehiculeException("Le nombre de places est obligatoire");
        if (campingCarDto.tarifJournalier() <= 0)
            throw new VehiculeException("Le tarif journalier est obligatoire");
        if (campingCarDto.kilometrage() <= 0)
            throw new VehiculeException("Le kilométrage est obligatoire");
        if (campingCarDto.typeEnergie() == null)
            throw new VehiculeException("Le type d'énergie est obligatoire");
        if (campingCarDto.transmission() == null)
            throw new VehiculeException("La transmission est obligatoire");
        if (campingCarDto.typeCampingCar() == null)
            throw new VehiculeException("Le type de camping car est obligatoire");
        if (campingCarDto.poidsPATC() <= 0) {
            throw new VehiculeException("Le poids à vide est obligatoire");
        } else if (campingCarDto.poidsPATC() < 3500) {
            campingCar.setPermisNecessaire(Permis.valueOf("B"));
        } else if (campingCarDto.poidsPATC() <= 7500) {
            campingCar.setPermisNecessaire(Permis.valueOf("C1"));
        } else {
            campingCar.setPermisNecessaire(Permis.valueOf("C"));
        }

        campingCarDao.save(campingCar);

    }

    @Override
    public void modifier(CampingCarDto campingCarDto, int id) throws VehiculeException {
        if (campingCarDto == null)
            throw new VehiculeException("Le camping car est nul");

        Optional<CampingCar> opt = campingCarDao.findById(id);
        if(opt.isEmpty())
            throw new VehiculeException("Le camping car à modifier n'existe pas");

        CampingCar campingCar = opt.get();

        if (campingCarDto.marque() != null && !campingCarDto.marque().isBlank())
            campingCar.setMarque(campingCarDto.marque());
        if (campingCarDto.modele() != null && !campingCarDto.modele().isBlank())
            campingCar.setModele(campingCarDto.modele());
        if (campingCarDto.couleur() != null && !campingCarDto.couleur().isBlank())
            campingCar.setCouleur(campingCarDto.couleur());
        if (campingCarDto.nombrePlaces() > 0)
            campingCar.setNombrePlaces(campingCarDto.nombrePlaces());
        if (campingCarDto.tarifJournalier() > 0)
            campingCar.setTarifJournalier(campingCarDto.tarifJournalier());
        if (campingCarDto.kilometrage() > 0)
            campingCar.setKilometrage(campingCarDto.kilometrage());
        if (campingCarDto.typeEnergie() != null)
            campingCar.setTypeEnergie(campingCarDto.typeEnergie());
        if (campingCarDto.transmission() != null)
            campingCar.setTransmission(campingCarDto.transmission());
        if (campingCarDto.typeCampingCar() != null)
            campingCar.setTypeCampingCar(campingCarDto.typeCampingCar());
        if (campingCarDto.poidsPATC() > 0) {
            campingCar.setPoidsPATC(campingCarDto.poidsPATC());
            if (campingCarDto.poidsPATC() < 3500) {
                campingCar.setPermisNecessaire(Permis.valueOf("B"));
            } else if (campingCarDto.poidsPATC() <= 7500) {
                campingCar.setPermisNecessaire(Permis.valueOf("C1"));
            } else {
                campingCar.setPermisNecessaire(Permis.valueOf("C"));
            }
        }

        campingCar.setActif(campingCarDto.actif());
        campingCar.setRetireDuParc(campingCarDto.retireDuParc());

        campingCarDao.save(campingCar);
    }

    @Override
    public void supprimer(int id) {
        campingCarDao.deleteById(id);
    }

    @Override
    public void supprimer(CampingCarDto campingCarDto) {
        campingCarDao.delete(campingCarMapper.campingCarDtoToCampingCar(campingCarDto));
    }

    @Override
    public CampingCarDto getCampingCarById(int id) {
        Optional<CampingCar> optCampingCar = campingCarDao.findById(id);
        return optCampingCar.map(campingCarMapper::campingCarToCampingCarDto).orElse(null);
    }

    @Override
    public List<CampingCarDto> getAllCampingCars() {
        return campingCarDao.findAll()
                .stream()
                .map(campingCarMapper::campingCarToCampingCarDto)
                .toList();
    }

    @Override
    public void desactiver(int id) throws VehiculeException {
        Optional<CampingCar> opt = campingCarDao.findById(id);
        if(opt.isEmpty())
            throw new VehiculeException("Le camping car à désactiver n'existe pas");

        CampingCar campingCar = opt.get();
        campingCar.setActif(false);

        campingCarDao.save(campingCar);
    }
}
