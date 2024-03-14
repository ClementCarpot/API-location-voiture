package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.MotoDto;

import java.util.List;

public interface MotoService {
    void ajouter(MotoDto motoDto) throws VehiculeException;

    void modifier(MotoDto motoDto, int id) throws VehiculeException;

    void supprimer(int id);

    void supprimer(MotoDto motoDto);

    MotoDto getMotoById(int id);

    List<MotoDto> getAllMotos();

    void desactiver(int id) throws VehiculeException;
}
