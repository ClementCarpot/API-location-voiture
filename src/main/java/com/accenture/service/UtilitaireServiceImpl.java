package com.accenture.service;

import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.vehicules.Utilitaire;
import com.accenture.dal.repository.UtilitaireDao;
import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.UtilitaireDto;
import com.accenture.service.mapper.UtilitaireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilitaireServiceImpl implements UtilitaireService {

    private final UtilitaireMapper utilitaireMapper;

    private final UtilitaireDao utilitaireDao;

    @Autowired
    public UtilitaireServiceImpl(UtilitaireDao utilitaireDao, UtilitaireMapper utilitaireMapper) {
        this.utilitaireDao = utilitaireDao;
        this.utilitaireMapper = utilitaireMapper;
    }

    @Override
    public void ajouter(UtilitaireDto utilitaireDto) throws VehiculeException {

        if (utilitaireDto == null)
            throw new VehiculeException("L'utilitaire est nul");

        Utilitaire utilitaire = utilitaireMapper.utilitaireDtoToUtilitaire(utilitaireDto);

        if (utilitaireDto.marque() == null || utilitaireDto.marque().isBlank())
            throw new VehiculeException("La marque est obligatoire");
        if (utilitaireDto.modele() == null || utilitaireDto.modele().isBlank())
            throw new VehiculeException("Le modèle est obligatoire");
        if (utilitaireDto.couleur() == null || utilitaireDto.couleur().isBlank())
            throw new VehiculeException("La couleur est obligatoire");
        if (utilitaireDto.nombrePlaces() <= 0)
            throw new VehiculeException("Le nombre de places est obligatoire");
        if (utilitaireDto.tarifJournalier() <= 0)
            throw new VehiculeException("Le tarif journalier est obligatoire");
        if (utilitaireDto.kilometrage() <= 0)
            throw new VehiculeException("Le kilométrage est obligatoire");
        if (utilitaireDto.typeEnergie() == null)
            throw new VehiculeException("Le type d'énergie est obligatoire");
        if (utilitaireDto.transmission() == null)
            throw new VehiculeException("La transmission est obligatoire");
        if (utilitaireDto.typeUtilitaire() == null)
            throw new VehiculeException("Le type d'utilitaire est obligatoire");
        if (utilitaireDto.chargeMax() <= 0)
            throw new VehiculeException("La charge maximale est obligatoire");
        if (utilitaireDto.poidsPATC() <= 0) {
            throw new VehiculeException("Le poids à vide est obligatoire");
        } else if (utilitaireDto.poidsPATC() < 3500) {
            utilitaire.setPermisNecessaire(Permis.valueOf("B"));
        } else if (utilitaireDto.poidsPATC() <= 7500) {
            utilitaire.setPermisNecessaire(Permis.valueOf("C1"));
        } else {
            utilitaire.setPermisNecessaire(Permis.valueOf("C"));
        }

        utilitaireDao.save(utilitaire);
    }

    @Override
    public void modifier(UtilitaireDto utilitaireDto, int id) throws VehiculeException {
        if (utilitaireDto == null)
            throw new VehiculeException("L'utilitaire est nul");

        Optional<Utilitaire> opt = utilitaireDao.findById(id);
        if (opt.isEmpty())
            throw new VehiculeException("L'utilitaire à modifier n'existe pas");

        Utilitaire utilitaire = opt.get();

        if (utilitaireDto.marque() != null && !utilitaireDto.marque().isBlank())
            utilitaire.setMarque(utilitaireDto.marque());
        if (utilitaireDto.modele() != null && !utilitaireDto.modele().isBlank())
            utilitaire.setModele(utilitaireDto.modele());
        if (utilitaireDto.couleur() != null && !utilitaireDto.couleur().isBlank())
            utilitaire.setCouleur(utilitaireDto.couleur());
        if (utilitaireDto.nombrePlaces() > 0)
            utilitaire.setNombrePlaces(utilitaireDto.nombrePlaces());
        if (utilitaireDto.tarifJournalier() > 0)
            utilitaire.setTarifJournalier(utilitaireDto.tarifJournalier());
        if (utilitaireDto.kilometrage() > 0)
            utilitaire.setKilometrage(utilitaireDto.kilometrage());
        if (utilitaireDto.typeEnergie() != null)
            utilitaire.setTypeEnergie(utilitaireDto.typeEnergie());
        if (utilitaireDto.transmission() != null)
            utilitaire.setTransmission(utilitaireDto.transmission());
        if (utilitaireDto.typeUtilitaire() != null)
            utilitaire.setTypeUtilitaire(utilitaireDto.typeUtilitaire());
        if (utilitaireDto.permisNecessaire() != null)
            utilitaire.setPermisNecessaire(utilitaireDto.permisNecessaire());
        if (utilitaireDto.chargeMax() > 0)
            utilitaire.setChargeMax(utilitaireDto.chargeMax());

        if (utilitaireDto.poidsPATC() <= 0) {
            throw new VehiculeException("Le poids à vide est obligatoire");
        } else if (utilitaireDto.poidsPATC() < 3500) {
            utilitaire.setPermisNecessaire(Permis.valueOf("B"));
        } else if (utilitaireDto.poidsPATC() <= 7500) {
            utilitaire.setPermisNecessaire(Permis.valueOf("C1"));
        } else {
            utilitaire.setPermisNecessaire(Permis.valueOf("C"));
        }

        utilitaire.setActif(utilitaireDto.actif());
        utilitaire.setRetireDuParc(utilitaireDto.retireDuParc());

        utilitaireDao.save(utilitaire);
    }

    @Override
    public void supprimer(int id) {
        utilitaireDao.deleteById(id);
    }

    @Override
    public void supprimer(UtilitaireDto utilitaireDto) {
        utilitaireDao.delete(utilitaireMapper.utilitaireDtoToUtilitaire(utilitaireDto));
    }

    @Override
    public UtilitaireDto getUtilitaireById(int id) {
        Optional<Utilitaire> opt = utilitaireDao.findById(id);
        return opt.map(utilitaireMapper::utilitaireToUtilitaireDto).orElse(null);
    }

    @Override
    public List<UtilitaireDto> getAllUtilitaires() {
        return utilitaireDao.findAll()
                .stream()
                .map(utilitaireMapper::utilitaireToUtilitaireDto)
                .toList();
    }

    @Override
    public void desactiver(int id) throws VehiculeException {
        Optional<Utilitaire> opt = utilitaireDao.findById(id);
        if (opt.isEmpty())
            throw new VehiculeException("L'utilitaire à désactiver n'existe pas");

        Utilitaire utilitaire = opt.get();
        utilitaire.setActif(false);

        utilitaireDao.save(utilitaire);
    }
}
