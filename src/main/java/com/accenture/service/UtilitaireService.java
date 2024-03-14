package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.UtilitaireDto;

import java.util.List;

public interface UtilitaireService {
    void ajouter(UtilitaireDto utilitaireDto) throws VehiculeException;

    void modifier(UtilitaireDto utilitaireDto, int id) throws VehiculeException;

    void supprimer(int id);

    void supprimer(UtilitaireDto utilitaireDto);

    UtilitaireDto getUtilitaireById(int id);

    List<UtilitaireDto> getAllUtilitaires();

    void desactiver(int id) throws VehiculeException;


}
