package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.CampingCarDto;

import java.util.List;

public interface CampingCarService {
    void ajouter(CampingCarDto campingCarDto) throws VehiculeException;

    void modifier(CampingCarDto campingCarDto, int id) throws VehiculeException;

    void supprimer(int id);

    void supprimer(CampingCarDto campingCarDto);

    CampingCarDto getCampingCarById(int id);

    List<CampingCarDto> getAllCampingCars();

    void desactiver(int id) throws VehiculeException;
}
