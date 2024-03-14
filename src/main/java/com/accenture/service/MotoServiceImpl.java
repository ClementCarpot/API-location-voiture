package com.accenture.service;

import com.accenture.dal.entity.vehicules.Moto;
import com.accenture.dal.entity.Permis;
import com.accenture.dal.repository.MotoDao;
import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.MotoDto;
import com.accenture.service.mapper.MotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoServiceImpl implements MotoService {

    private final MotoDao motoDao;

    private final MotoMapper motoMapper;

    @Autowired
    public MotoServiceImpl(MotoDao motoDao, MotoMapper motoMapper) {
        this.motoDao = motoDao;
        this.motoMapper = motoMapper;
    }

    @Override
    public void ajouter(MotoDto motoDto) throws VehiculeException {

        if (motoDto == null)
            throw new VehiculeException("La moto est nulle");
        if (motoDto.marque() == null || motoDto.marque().isBlank())
            throw new VehiculeException("La marque est obligatoire");
        if (motoDto.modele() == null || motoDto.modele().isBlank())
            throw new VehiculeException("Le modèle est obligatoire");
        if (motoDto.couleur() == null || motoDto.couleur().isBlank())
            throw new VehiculeException("La couleur est obligatoire");
        if (motoDto.tarifJournalier() <= 0)
            throw new VehiculeException("Le tarif journalier est obligatoire");
        if (motoDto.kilometrage() <= 0)
            throw new VehiculeException("Le kilométrage est obligatoire");
        if (motoDto.typeMoto() == null)
            throw new VehiculeException("Le type de moto est obligatoire");
        if (motoDto.cylindree() <= 0)
            throw new VehiculeException("La cylindrée est obligatoire");
        if (motoDto.poids() <= 0)
            throw new VehiculeException("Le poids est obligatoire");
        if (motoDto.puissanceKW() <= 0)
            throw new VehiculeException("La puissance est obligatoire");
        if (motoDto.hauteurSelle() <= 0)
            throw new VehiculeException("La hauteur de selle est obligatoire");
        if (motoDto.transmission() == null)
            throw new VehiculeException("La transmission est obligatoire");

        Moto moto = motoMapper.motoDtoToMoto(motoDto);

        if (motoDto.cylindree() <= 125 && motoDto.puissanceKW() <= 11)
            moto.setPermisNecessaire(Permis.valueOf("A1"));
        else if (motoDto.puissanceKW() <= 35)
            moto.setPermisNecessaire(Permis.valueOf("A2"));
        else
            moto.setPermisNecessaire(Permis.valueOf("A"));

        motoDao.save(moto);
    }

    @Override
    public void modifier(MotoDto motoDto, int id) throws VehiculeException {
        if (motoDto == null)
            throw new VehiculeException("La moto est nulle");

        Optional<Moto> opt = motoDao.findById(id);
        if(opt.isEmpty())
            throw new VehiculeException("La moto à modifier n'existe pas");

        Moto moto = opt.get();

        if (motoDto.marque() != null && !motoDto.marque().isBlank())
            moto.setMarque(motoDto.marque());
        if (motoDto.modele() != null && !motoDto.modele().isBlank())
            moto.setModele(motoDto.modele());
        if (motoDto.couleur() != null && !motoDto.couleur().isBlank())
            moto.setCouleur(motoDto.couleur());
        if (motoDto.tarifJournalier() > 0)
            moto.setTarifJournalier(motoDto.tarifJournalier());
        if (motoDto.kilometrage() > 0)
            moto.setKilometrage(motoDto.kilometrage());
        if (motoDto.typeMoto() != null)
            moto.setTypeMoto(motoDto.typeMoto());
        if (motoDto.cylindree() > 0)
            moto.setCylindree(motoDto.cylindree());
        if (motoDto.poids() > 0)
            moto.setPoids(motoDto.poids());
        if (motoDto.puissanceKW() > 0)
            moto.setPuissanceKW(motoDto.puissanceKW());
        if (motoDto.hauteurSelle() > 0)
            moto.setHauteurSelle(motoDto.hauteurSelle());
        if (motoDto.transmission() != null)
            moto.setTransmission(motoDto.transmission());

        if (motoDto.cylindree() <= 125 && motoDto.puissanceKW() <= 11)
            moto.setPermisNecessaire(Permis.valueOf("A1"));
        else if (motoDto.puissanceKW() <= 35)
            moto.setPermisNecessaire(Permis.valueOf("A2"));
        else
            moto.setPermisNecessaire(Permis.valueOf("A"));

        moto.setActif(motoDto.actif());
        moto.setRetireDuParc(motoDto.retireDuParc());


        motoDao.save(moto);
    }

    @Override
    public void supprimer(int id) {
        motoDao.deleteById(id);
    }

    @Override
    public void supprimer(MotoDto motoDto) {
        motoDao.delete(motoMapper.motoDtoToMoto(motoDto));
    }

    @Override
    public MotoDto getMotoById(int id) {
        Optional<Moto> opt = motoDao.findById(id);
        return opt.map(motoMapper::motoToMotoDto).orElse(null);
    }

    @Override
    public List<MotoDto> getAllMotos() {
        return motoDao.findAll()
                .stream()
                .map(motoMapper::motoToMotoDto)
                .toList();
    }

    @Override
    public void desactiver(int id) throws VehiculeException {
        Optional<Moto> opt = motoDao.findById(id);
        if(opt.isEmpty())
            throw new VehiculeException("La moto à désactiver n'existe pas");

        Moto moto = opt.get();
        moto.setActif(false);
        motoDao.save(moto);
    }
}
