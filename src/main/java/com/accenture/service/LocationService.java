package com.accenture.service;

import com.accenture.exception.LocationException;
import com.accenture.service.dto.LocationDto;

import java.time.LocalDate;
import java.util.List;

public interface LocationService {
    void ajouter(LocationDto locationDto) throws LocationException;

    void modifier(LocationDto locationDto, int id) throws LocationException;

    void supprimer(int id);

    void supprimer(LocationDto locationDto);

    LocationDto getLocationById(int id);

    List<LocationDto> getAllLocations();

    List<LocationDto> getLocationsByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin);
    List<LocationDto> getLocationsByDateDebutNotAndDateFinNot(LocalDate dateDebut, LocalDate dateFin);

    List<LocationDto> getLocationsBetweenDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin);
}
