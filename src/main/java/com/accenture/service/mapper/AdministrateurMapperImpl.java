package com.accenture.service.mapper;

import com.accenture.dal.entity.utilisateurs.Administrateur;
import com.accenture.dal.entity.utilisateurs.Role;
import com.accenture.service.dto.AdministrateurDto;
import com.accenture.service.dto.AdministrateurResponseDto;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-01T11:02:15+0100",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class AdministrateurMapperImpl implements AdministrateurMapper {

    @Override
    public Administrateur administrateurDtoToAdministrateur(AdministrateurDto dto) {
        if (dto == null) {
            return null;
        }

        Administrateur administrateur = new Administrateur();

        administrateur.setId(dto.id());
        administrateur.setNom(dto.nom());
        administrateur.setPrenom(dto.prenom());
        administrateur.setFonction(dto.fonction());
        administrateur.setEmail(dto.email());
        administrateur.setMotDePasse(dto.motDePasse());
        administrateur.setRole(dto.role());

        return administrateur;
    }

    @Override
    public AdministrateurDto administrateurToAdministrateurDto(Administrateur entity) {
        if (entity == null) {
            return null;
        }

        int id = 0;
        String nom = null;
        String prenom = null;
        String fonction = null;
        String email = null;
        String motDePasse = null;
        Role role = null;

        id = entity.getId();
        nom = entity.getNom();
        prenom = entity.getPrenom();
        fonction = entity.getFonction();
        email = entity.getEmail();
        motDePasse = entity.getMotDePasse();
        role = entity.getRole();

        AdministrateurDto administrateurDto = new AdministrateurDto(id, nom, prenom, fonction, email, motDePasse, role);

        return administrateurDto;
    }

    @Override
    public AdministrateurResponseDto administrateurDtoToAdministrateurResponseDto(AdministrateurDto administrateurDto) {
        if (administrateurDto == null) {
            return null;
        }

        String nom = null;
        String prenom = null;
        String email = null;
        String fonction = null;
        Role role = null;

        nom = administrateurDto.nom();
        prenom = administrateurDto.prenom();
        email = administrateurDto.email();
        fonction = administrateurDto.fonction();
        role = administrateurDto.role();

        AdministrateurResponseDto administrateurResponseDto = new AdministrateurResponseDto(nom, prenom, email, fonction, role);

        return administrateurResponseDto;
    }

    @Override
    public AdministrateurResponseDto administrateurToAdministrateurResponseDto(Administrateur administrateur) {
        if (administrateur == null) {
            return null;
        }

        String nom = null;
        String prenom = null;
        String email = null;
        String fonction = null;
        Role role = null;

        nom = administrateur.getNom();
        prenom = administrateur.getPrenom();
        email = administrateur.getEmail();
        fonction = administrateur.getFonction();
        role = administrateur.getRole();

        AdministrateurResponseDto administrateurResponseDto = new AdministrateurResponseDto(nom, prenom, email, fonction, role);

        return administrateurResponseDto;
    }
}
