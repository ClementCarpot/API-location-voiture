package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.VeloDto;

import java.util.List;

public interface VeloService {
    void ajouter(VeloDto veloDto) throws VehiculeException;

    void modifier(VeloDto veloDto, int id) throws VehiculeException;

    void supprimer(int id);

    void supprimer(VeloDto veloDto);

    VeloDto getVeloById(int id);

    List<VeloDto> getAllVelos();

    void desactiver(int id) throws VehiculeException;
}
