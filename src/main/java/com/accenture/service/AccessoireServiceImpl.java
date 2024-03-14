package com.accenture.service;

import com.accenture.dal.entity.Accessoire;
import com.accenture.dal.repository.AccessoireDao;
import com.accenture.exception.AccessoireException;
import com.accenture.service.dto.AccessoireDto;
import com.accenture.service.mapper.AccessoireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessoireServiceImpl implements AccessoireService {

    private final AccessoireDao accessoireDao;

    private final AccessoireMapper accessoireMapper;

    @Autowired
    public AccessoireServiceImpl(AccessoireDao accessoireDao, AccessoireMapper accessoireMapper) {
        this.accessoireDao = accessoireDao;
        this.accessoireMapper = accessoireMapper;
    }

    @Override
    public void ajouter(AccessoireDto accessoireDto) throws AccessoireException {

        if (accessoireDto == null)
            throw new AccessoireException("L'accessoire est nul");
        if (accessoireDto.nom() == null || accessoireDto.nom().isBlank())
            throw new AccessoireException("Le nom est obligatoire");
        if (accessoireDto.montantUnitaire() <= 0)
            throw new AccessoireException("Le montant unitaire est obligatoire");
        if (accessoireDto.limite() <= 0)
            throw new AccessoireException("La limite est obligatoire");
        if (accessoireDto.accessoirePourTypeVehicule() == null)
            throw new AccessoireException("Le type de véhicule est obligatoire");


        Accessoire accessoire = accessoireMapper.accessoireDtoToAccessoire(accessoireDto);

        accessoireDao.save(accessoire);
    }

    @Override
    public void modifier(AccessoireDto accessoireDto, int id) throws AccessoireException {
        if (accessoireDto == null)
            throw new AccessoireException("L'accessoire est nul");

        Optional<Accessoire> opt = accessoireDao.findById(id);
        if (opt.isEmpty())
            throw new AccessoireException("L'accessoire n'existe pas");

        Accessoire accessoire = opt.get();

        if (accessoireDto.nom() != null && !accessoireDto.nom().isBlank())
            accessoire.setNom(accessoireDto.nom());
        if (accessoireDto.montantUnitaire() > 0)
            accessoire.setMontantUnitaire(accessoireDto.montantUnitaire());
        if (accessoireDto.limite() > 0)
            accessoire.setLimite(accessoireDto.limite());
        if (accessoireDto.accessoirePourTypeVehicule() != null)
            accessoire.setAccessoirePourTypeVehicule(accessoireDto.accessoirePourTypeVehicule());

        accessoireDao.save(accessoire);

    }

    @Override
    public void supprimer(int id) {
        accessoireDao.deleteById(id);
    }

    @Override
    public void supprimer(AccessoireDto accessoireDto) {
        accessoireDao.delete(accessoireMapper.accessoireDtoToAccessoire(accessoireDto));
    }

    @Override
    public AccessoireDto getAccessoireById(int id) {
        Optional<Accessoire> opt = accessoireDao.findById(id);
        return opt.map(accessoireMapper::accessoireToAccessoireDto).orElse(null);
    }

    @Override
    public List<AccessoireDto> getAllAccessoires() {
        return accessoireDao.findAll()
                .stream()
                .map(accessoireMapper::accessoireToAccessoireDto)
                .toList();
    }
}
