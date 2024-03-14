package com.accenture.service;

import com.accenture.dal.entity.vehicules.Velo;
import com.accenture.dal.repository.VeloDao;
import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.VeloDto;
import com.accenture.service.mapper.VeloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeloServiceImpl implements VeloService {

    private final VeloDao veloDao;
    private final VeloMapper veloMapper;

    @Autowired
    public VeloServiceImpl(VeloDao veloDao, VeloMapper veloMapper) {
        this.veloDao = veloDao;
        this.veloMapper = veloMapper;
    }

    @Override
    public void ajouter(VeloDto veloDto) throws VehiculeException {

        if (veloDto == null)
            throw new VehiculeException("Le vélo est nul");
        if (veloDto.marque() == null || veloDto.marque().isBlank())
            throw new VehiculeException("La marque est obligatoire");
        if (veloDto.modele() == null || veloDto.modele().isBlank())
            throw new VehiculeException("Le modèle est obligatoire");
        if (veloDto.couleur() == null || veloDto.couleur().isBlank())
            throw new VehiculeException("La couleur est obligatoire");
        if (veloDto.tarifJournalier() <= 0)
            throw new VehiculeException("Le tarif journalier est obligatoire");
        if (veloDto.kilometrage() <= 0)
            throw new VehiculeException("Le kilométrage est obligatoire");
        if (veloDto.typeVelo() == null)
            throw new VehiculeException("Le type de vélo est obligatoire");
        if (veloDto.tailleCadre() == null || veloDto.tailleCadre().isBlank())
            throw new VehiculeException("La taille du cadre est obligatoire");
        if (veloDto.poidsKg() <= 0)
            throw new VehiculeException("Le poids est obligatoire");
        if (veloDto.isElectrique() && veloDto.batterie().getAutonomieKm() <= 0 || veloDto.isElectrique() && veloDto.batterie().getCapaciteKwh() <= 0)
            throw new VehiculeException("La batterie est obligatoire pour un vélo électrique");

        Velo velo = veloMapper.veloDtoToVelo(veloDto);

        veloDao.save(velo);
    }

    @Override
    public void modifier(VeloDto veloDto, int id) throws VehiculeException {
        if (veloDto == null)
            throw new VehiculeException("Le vélo est nul");

        Optional<Velo> opt = veloDao.findById(id);
        if (opt.isEmpty())
            throw new VehiculeException("Le vélo à modifier n'existe pas");

        Velo velo = opt.get();

        if (veloDto.marque() != null)
            velo.setMarque(veloDto.marque());
        if (veloDto.modele() != null)
            velo.setModele(veloDto.modele());
        if (veloDto.couleur() != null)
            velo.setCouleur(veloDto.couleur());
        if (veloDto.tarifJournalier() > 0)
            velo.setTarifJournalier(veloDto.tarifJournalier());
        if (veloDto.kilometrage() > 0)
            velo.setKilometrage(veloDto.kilometrage());
        if (veloDto.typeVelo() != null)
            velo.setTypeVelo(veloDto.typeVelo());
        if (veloDto.tailleCadre() != null)
            velo.setTailleCadre(veloDto.tailleCadre());
        if (veloDto.poidsKg() > 0)
            velo.setPoidsKg(veloDto.poidsKg());
        if (veloDto.isElectrique() && veloDto.batterie().getAutonomieKm() > 0 || veloDto.isElectrique() && veloDto.batterie().getCapaciteKwh() > 0)
            velo.setBatterie(veloDto.batterie());
        if (!veloDto.isElectrique()) {
            velo.setBatterie(null);
        }

        velo.setElectrique(veloDto.isElectrique());
        velo.setActif(veloDto.actif());
        velo.setRetireDuParc(veloDto.retireDuParc());
        velo.setFreinsDisque(veloDto.freinsDisque());

        veloDao.save(velo);
    }

    @Override
    public void supprimer(int id) {
        veloDao.deleteById(id);
    }

    @Override
    public void supprimer(VeloDto veloDto) {
        veloDao.delete(veloMapper.veloDtoToVelo(veloDto));
    }

    @Override
    public VeloDto getVeloById(int id) {
        Optional<Velo> opt = veloDao.findById(id);
        return opt.map(veloMapper::veloToVeloDto).orElse(null);
    }

    @Override
    public List<VeloDto> getAllVelos() {
        return veloDao.findAll()
                .stream()
                .map(veloMapper::veloToVeloDto)
                .toList();
    }

    @Override
    public void desactiver(int id) throws VehiculeException {
        Optional<Velo> opt = veloDao.findById(id);
        if (opt.isEmpty())
            throw new VehiculeException("Le vélo à désactiver n'existe pas");

        Velo velo = opt.get();
        velo.setActif(false);

        veloDao.save(velo);
    }
}
