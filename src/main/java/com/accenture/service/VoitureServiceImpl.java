package com.accenture.service;

import com.accenture.dal.entity.Permis;
import com.accenture.dal.entity.vehicules.Voiture;
import com.accenture.dal.repository.VoitureDao;
import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.VoitureDto;
import com.accenture.service.mapper.VoitureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureServiceImpl implements VoitureService {

    private final VoitureMapper voitureMapper;

    private final VoitureDao voitureDao;

    @Autowired
    private VoitureServiceImpl(VoitureDao voitureDao, VoitureMapper voitureMapper) {
        this.voitureDao = voitureDao;
        this.voitureMapper = voitureMapper;
    }

    @Override
    public void ajouter(VoitureDto voitureDto) throws VehiculeException {

        if (voitureDto == null)
            throw new VehiculeException("La voiture est nulle");

        Voiture voiture = voitureMapper.voitureDtoToVoiture(voitureDto);

        if (voitureDto.marque() == null || voitureDto.marque().isBlank())
            throw new VehiculeException("La marque est obligatoire");
        if (voitureDto.modele() == null || voitureDto.modele().isBlank())
            throw new VehiculeException("Le modèle est obligatoire");
        if (voitureDto.couleur() == null || voitureDto.couleur().isBlank())
            throw new VehiculeException("La couleur est obligatoire");
        if (voitureDto.nombrePlaces() <= 0) {
            throw new VehiculeException("Le nombre de places est obligatoire");
        } else if (voitureDto.nombrePlaces() < 10) {
            voiture.setPermisNecessaire(Permis.valueOf("B"));
        } else if (voitureDto.nombrePlaces() <= 16) {
            voiture.setPermisNecessaire(Permis.valueOf("D1"));
        } else {
            voiture.setPermisNecessaire(Permis.valueOf("D"));
        }
        if (voitureDto.nombrePortes() <= 0)
            throw new VehiculeException("Le nombre de portes est obligatoire");
        if (voitureDto.tarifJournalier() <= 0)
            throw new VehiculeException("Le tarif journalier est obligatoire");
        if (voitureDto.kilometrage() <= 0)
            throw new VehiculeException("Le kilométrage est obligatoire");
        if (voitureDto.typeEnergie() == null)
            throw new VehiculeException("Le type d'énergie est obligatoire");
        if (voitureDto.transmission() == null)
            throw new VehiculeException("La transmission est obligatoire");
        if (voitureDto.typeVoiture() == null)
            throw new VehiculeException("Le type de voiture est obligatoire");
        if (voitureDto.nombreBagages() <= 0)
            throw new VehiculeException("Le nombre de bagages est obligatoire");

        voitureDao.save(voiture);
    }

    @Override
    public void modifier(VoitureDto voitureDto, int id) throws VehiculeException {
        if (voitureDto == null)
            throw new VehiculeException("La voiture est nulle");

        Optional<Voiture> opt = voitureDao.findById(id);
        if (opt.isEmpty())
            throw new VehiculeException("La voiture à modifier n'existe pas");

        Voiture voiture = opt.get();

        if (voitureDto.marque() != null && !voitureDto.marque().isBlank())
            voiture.setMarque(voitureDto.marque());
        if (voitureDto.modele() != null && !voitureDto.modele().isBlank())
            voiture.setModele(voitureDto.modele());
        if (voitureDto.couleur() != null && !voitureDto.couleur().isBlank())
            voiture.setCouleur(voitureDto.couleur());
        if (voitureDto.nombrePlaces() > 0)
            voiture.setNombrePlaces(voitureDto.nombrePlaces());
        if (voitureDto.tarifJournalier() > 0)
            voiture.setTarifJournalier(voitureDto.tarifJournalier());
        if (voitureDto.kilometrage() > 0)
            voiture.setKilometrage(voitureDto.kilometrage());
        if (voitureDto.typeEnergie() != null)
            voiture.setTypeEnergie(voitureDto.typeEnergie());
        if (voitureDto.nombrePortes() > 0)
            voiture.setNombrePortes(voitureDto.nombrePortes());
        if (voitureDto.transmission() != null)
            voiture.setTransmission(voitureDto.transmission());
        if (voitureDto.nombreBagages() > 0)
            voiture.setNombreBagages(voitureDto.nombreBagages());
        if (voitureDto.typeVoiture() != null)
            voiture.setTypeVoiture(voitureDto.typeVoiture());

        if (voitureDto.nombrePlaces() < 10) {
            voiture.setPermisNecessaire(Permis.valueOf("B"));
        } else {
            voiture.setPermisNecessaire(Permis.valueOf("D1"));
        }

        voiture.setClimatisation(voitureDto.climatisation());
        voiture.setActif(voitureDto.actif());
        voiture.setRetireDuParc(voitureDto.retireDuParc());

        voitureDao.save(voiture);

    }

    @Override
    public void supprimer(int id) {
        voitureDao.deleteById(id);
    }

    @Override
    public void supprimer(VoitureDto voitureDto) {
        voitureDao.delete(voitureMapper.voitureDtoToVoiture(voitureDto));
    }

    @Override
    public VoitureDto getVoitureById(int id) {
        Optional<Voiture> opt = voitureDao.findById(id);
        return opt.map(voitureMapper::voitureToVoitureDto).orElse(null);
    }

    @Override
    public List<VoitureDto> getAllVoitures() {
        return voitureDao.findAll()
                .stream()
                .map(voitureMapper::voitureToVoitureDto)
                .toList();
    }

    @Override
    public void desactiver(int id) throws VehiculeException {
        Optional<Voiture> opt = voitureDao.findById(id);
        if (opt.isEmpty())
            throw new VehiculeException("La voiture à désactiver n'existe pas");

        Voiture voiture = opt.get();
        voiture.setActif(false);
        voitureDao.save(voiture);
    }
}
