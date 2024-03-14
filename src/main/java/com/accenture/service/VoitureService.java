package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.VoitureDto;

import java.util.List;

public interface VoitureService {
    void ajouter(VoitureDto voitureDto) throws VehiculeException;

    void modifier(VoitureDto voitureDto, int id) throws VehiculeException;

    void supprimer(int id);

    void supprimer(VoitureDto voitureDto);

    VoitureDto getVoitureById(int id);

    List<VoitureDto> getAllVoitures();

    void desactiver(int id) throws VehiculeException;
}
