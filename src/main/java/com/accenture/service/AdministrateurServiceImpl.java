package com.accenture.service;

import com.accenture.dal.entity.utilisateurs.Administrateur;
import com.accenture.dal.repository.AdministrateurDao;
import com.accenture.exception.UtilisateurException;
import com.accenture.service.dto.AdministrateurDto;
import com.accenture.service.dto.AdministrateurResponseDto;
import com.accenture.service.mapper.AdministrateurMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrateurServiceImpl implements AdministrateurService {

    private final AdministrateurDao administrateurDao;
    private final AdministrateurMapper administrateurMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AdministrateurServiceImpl(AdministrateurDao administrateurDao, AdministrateurMapper administrateurMapper, PasswordEncoder passwordEncoder) {
        this.administrateurDao = administrateurDao;
        this.administrateurMapper = administrateurMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void ajouter(AdministrateurDto administrateurDto) throws UtilisateurException {
        if (administrateurDto == null)
            throw new UtilisateurException("L'administrateur est nul");
        if (existe(administrateurDto.email()))
            throw new UtilisateurException("Cet email est déjà utilisé");

        Administrateur administrateur = administrateurMapper.administrateurDtoToAdministrateur(administrateurDto);

        setPassword(administrateurDto, administrateur);
        administrateur.setEmail(administrateurDto.email().toLowerCase());

        administrateurDao.save(administrateur);
    }


    @Override
    public void modifier(AdministrateurDto administrateurDto, int id) throws UtilisateurException {
        if (administrateurDto == null)
            throw new UtilisateurException("L'administrateur est nul");
        Optional<Administrateur> opt = administrateurDao.findById(id);
        if (opt.isEmpty())
            throw new UtilisateurException("L'administrateur à modifier n'existe pas");

        Administrateur administrateur = opt.get();

        if (administrateurDto.motDePasse() != null && !administrateurDto.motDePasse().isBlank())
            setPassword(administrateurDto, administrateur);
        if (administrateurDto.nom() != null && !administrateurDto.nom().isBlank())
            administrateur.setNom(administrateurDto.nom());
        if (administrateurDto.prenom() != null && !administrateurDto.prenom().isBlank())
            administrateur.setPrenom(administrateurDto.prenom());
        if (administrateurDto.fonction() != null && !administrateurDto.fonction().isBlank())
            administrateur.setFonction(administrateurDto.fonction());
        if (administrateurDto.email() != null && !administrateurDto.email().isBlank()) {
            if (existe(administrateurDto.email())) {
                throw new UtilisateurException("Cet email est déjà utilisé");
            } else {
                administrateur.setEmail(administrateurDto.email().toLowerCase());
            }
        }
        if (administrateurDto.role() != null)
            administrateur.setRole(administrateurDto.role());

        administrateurDao.save(administrateur);
    }

    @Override
    public void supprimer(int id) {
        administrateurDao.deleteById(id);
    }

    @Override
    public void supprimer(AdministrateurDto administrateurDto) {
        administrateurDao.delete(administrateurMapper.administrateurDtoToAdministrateur(administrateurDto));
    }

    @Override
    public AdministrateurDto getAdministrateurById(int id) {
        return administrateurMapper.administrateurToAdministrateurDto(administrateurDao.findById(id).orElse(null));
    }

    @Override
    public AdministrateurResponseDto getAdministrateurByIdWithoutPassword(int id) {
        return administrateurMapper.administrateurToAdministrateurResponseDto(administrateurDao.findById(id).orElse(null));
    }

    @Override
    public List<AdministrateurDto> getAllAdministrateurs() {
        return administrateurDao.findAll()
                .stream()
                .map(administrateurMapper::administrateurToAdministrateurDto)
                .toList();
    }

    @Override
    public List<AdministrateurResponseDto> getAllAdministrateursWithoutPassword() {
        return administrateurDao.findAll()
                .stream()
                .map(administrateurMapper::administrateurToAdministrateurResponseDto)
                .toList();
    }

    @Override
    public boolean existe(String email) {
        return administrateurDao.existsByEmailIgnoreCase(email);
    }

    @Override
    public AdministrateurDto getAdministrateurByEmailIgnoreCaseAndMotDePasse(String email, String motDePasse) {
        return administrateurMapper.administrateurToAdministrateurDto(administrateurDao.findByEmailIgnoreCaseAndMotDePasse(email, motDePasse));
    }

    @Override
    public void desactiver(AdministrateurDto administrateurDto) throws UtilisateurException {
        Administrateur administrateur = administrateurDao.findById(administrateurDto.id()).orElseThrow(() -> new UtilisateurException("L'administrateur n'existe pas"));
        administrateur.setDesactive(true);
        administrateurDao.save(administrateur);
    }

    private void setPassword(AdministrateurDto administrateurDto, Administrateur administrateur) throws UtilisateurException {
        // 8 à 16 caractères, au moins une majuscule, une lettre minuscule, un chiffre et un caractère spécial
        if (!administrateurDto.motDePasse().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[&#@\\-_§]).{8,16}$")) {
            throw new UtilisateurException("Le mot de passe doit contenir 8 à 16 caractères, " +
                    "au moins une majuscule, une lettre minuscule, un chiffre et un caractère spécial");
        } else {
            administrateur.setMotDePasse(passwordEncoder.encode(administrateurDto.motDePasse()));
        }
    }
}
