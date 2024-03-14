package com.accenture.service;

import com.accenture.exception.UtilisateurException;
import com.accenture.service.dto.AdministrateurDto;
import com.accenture.service.dto.AdministrateurResponseDto;

import java.util.List;

public interface AdministrateurService {
    void ajouter(AdministrateurDto administrateurDto) throws UtilisateurException;
    void modifier(AdministrateurDto administrateurDto, int id) throws UtilisateurException;
    void supprimer(int id);
    void supprimer(AdministrateurDto administrateurDto);
    AdministrateurDto getAdministrateurById(int id);
    AdministrateurResponseDto getAdministrateurByIdWithoutPassword(int id);
    List<AdministrateurDto> getAllAdministrateurs();
    List<AdministrateurResponseDto> getAllAdministrateursWithoutPassword();
    boolean existe(String email);
    AdministrateurDto getAdministrateurByEmailIgnoreCaseAndMotDePasse(String email, String motDePasse);

    void desactiver(AdministrateurDto administrateur) throws UtilisateurException;
}
